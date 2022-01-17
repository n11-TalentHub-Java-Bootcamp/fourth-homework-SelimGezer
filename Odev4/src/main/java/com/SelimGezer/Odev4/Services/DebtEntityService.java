package com.SelimGezer.Odev4.Services;

import com.SelimGezer.Odev4.Dao.DebtDao;
import com.SelimGezer.Odev4.Dtos.DebtAddResponceDto;
import com.SelimGezer.Odev4.Dtos.DebtRequestDto;
import com.SelimGezer.Odev4.Dtos.UserResponceDto;
import com.SelimGezer.Odev4.Entities.Debt;
import com.SelimGezer.Odev4.Entities.User;
import com.SelimGezer.Odev4.Enum.DeptType;
import com.SelimGezer.Odev4.Exception.DebtNotFound;
import com.SelimGezer.Odev4.Mapper.DebtMapper;
import com.SelimGezer.Odev4.Util.Constants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class DebtEntityService {

    private final DebtDao debtDao;
    private final UserEntityService userEntityService;

    public DebtEntityService(DebtDao debtDao, UserEntityService userEntityService) {
        this.debtDao = debtDao;
        this.userEntityService = userEntityService;
    }


    @Transactional()
    public DebtAddResponceDto addDebt(DebtRequestDto debtRequestDto, DeptType deptType) {
        UserResponceDto userById = userEntityService.getUserById(debtRequestDto.getUserId());

        Debt debt;
        debt= DebtMapper.toEntity(userById,debtRequestDto,deptType);

        Debt save = debtDao.save(debt);
        DebtAddResponceDto debtAddResponceDto = DebtMapper.toAddDto(save);
        return debtAddResponceDto;

    }

    public List<DebtAddResponceDto> getAllDebt() {
        return debtDao.findAll().stream().map(debt -> new DebtAddResponceDto(debt.getId(),debt.getUser(),debt.getDebtDescription(),debt.getMainDebtAmount(),debt.getCreationDate(),debt.getMaturityDate(),debt.getDebtType(),debt.getConnectedDebt())).collect(Collectors.toList());
    }

    public List<DebtAddResponceDto> getAllDebtBetweenDate(Date firstDate, Date endDate) {
        List<DebtAddResponceDto> all= debtDao.findByCreationDateBetween(firstDate,endDate).stream().map(debt ->  new DebtAddResponceDto(debt.getId(),debt.getUser(),debt.getDebtDescription(),debt.getMainDebtAmount(),debt.getCreationDate(),debt.getMaturityDate(),debt.getDebtType(),debt.getConnectedDebt())).collect(Collectors.toList());
        return all;
    }

    public List<DebtAddResponceDto> getAllDebtByUserId(Long id) {
        return debtDao.findByUserIdAndRemanderDebtAmountGreaterThan(id,0).stream().map(debt ->  new DebtAddResponceDto(debt.getId(),debt.getUser(),debt.getDebtDescription(),debt.getMainDebtAmount(),debt.getCreationDate(),debt.getMaturityDate(),debt.getDebtType(),debt.getConnectedDebt())).collect(Collectors.toList());
    }

    public List<DebtAddResponceDto> getAllDebtByUserIdAndMaturityDate(Long id, Boolean state)  {
        User checkUser = userEntityService.checkUserById(id);
        if(state){
            try {
                return debtDao.findAllByUserIdAndMaturityDateAfter(checkUser.getId(),Constants.simpleDateFormat.parse(LocalDate.now().toString())).stream().map(debt ->  new DebtAddResponceDto(debt.getId(),debt.getUser(),debt.getDebtDescription(),debt.getMainDebtAmount(),debt.getCreationDate(),debt.getMaturityDate(),debt.getDebtType(),debt.getConnectedDebt())).collect(Collectors.toList());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            try {
                return debtDao.findAllByUserIdMaturityDateBefore(checkUser.getId(),Constants.simpleDateFormat.parse(LocalDate.now().toString())).stream().map(debt ->  new DebtAddResponceDto(debt.getId(),debt.getUser(),debt.getDebtDescription(),debt.getMainDebtAmount(),debt.getCreationDate(),debt.getMaturityDate(),debt.getDebtType(),debt.getConnectedDebt())).collect(Collectors.toList());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("Beklenmeyen Hata!");
    }

    public int getTotalDebtAmount(Long id) {
        User checkUser = userEntityService.checkUserById(id);
        return debtDao.findSumAllMainDebt(id);
    }

    public List<Debt> getTotalOverdueAmountList(Long id)  {
        User checkUser=userEntityService.checkUserById(id);
        try {
            return debtDao.findSumOverdue(id,Constants.simpleDateFormat.parse(LocalDate.now().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Beklenmeyen Hata!");
    }

    public int getTotalOverdueAmount(Long id)  {
        List<Debt> totalOverdueAmountList = getTotalOverdueAmountList(id);
        int total=0;
        for (Debt debt:totalOverdueAmountList) {
            total+=debt.getMainDebtAmount();
        }
        return total;
    }

    public float getTotalLatefeeAmount(Long id, Date paymentDate) throws ParseException {
        List<Debt> totalOverdueAmountList = getTotalOverdueAmountList(id); //Vadesi geçmişlerin listesi Şuanki tarihe göre hesaplıyor!

        Date dateParse2018 = null;
        try {
            dateParse2018 = Constants.simpleDateFormat.parse("2018-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        float totalLateFee=0;
        for (Debt debt:totalOverdueAmountList) {

            long day=0;
            if(paymentDate.after(dateParse2018) && paymentDate.after(debt.getMaturityDate())){

                day = dateDifference(dateParse2018, debt.getMaturityDate());
                totalLateFee +=  lateFeeCalculate(debt.getMainDebtAmount(),day,Constants.BEFORE_2018);

                day = dateDifference(paymentDate, dateParse2018);
                totalLateFee += lateFeeCalculate(debt.getMainDebtAmount(),day,Constants.AFTER_2018);

            }else if(paymentDate.before(dateParse2018)){

                day = dateDifference(paymentDate, debt.getMaturityDate());
                totalLateFee += lateFeeCalculate(debt.getMainDebtAmount(),day,Constants.BEFORE_2018); //debt.getMainDebtAmount() * day * ((Constants.BEFORE_2018/30)/100);
            }
        }
        return totalLateFee;
    }


    public long dateDifference(Date firstDate,Date secondDate) throws ParseException {
        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long day = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        return day;
    }

    public float lateFeeCalculate(float mainDebtAmount,long day,float ratio){
       return (float) Math.ceil(mainDebtAmount * day * ((ratio/30)/100));
    }

    public Debt checkDebtById(Long id){
        Optional<Debt> optionalDebt = debtDao.findById(id);
        if(optionalDebt.isPresent()){
            return optionalDebt.get();
        }else{
            throw new DebtNotFound(String.format("%d id sine sahip bir borç bulunamadı!", id));
        }
    }

    @Transactional()
    public String updateDebt(Long debtId,float paymentAmount){
        Optional<Debt> optionalDebt = debtDao.findById(debtId);
        if(optionalDebt.isPresent()){
            Debt debt=optionalDebt.get();
            debt.setRemanderDebtAmount(debt.getRemanderDebtAmount()-paymentAmount);
            debtDao.save(debt);
            return "Başarıyla Güncellendi!";
        }else{
            throw new DebtNotFound(String.format("Belirtilen %d id li bir borç bulunamadı!",debtId));
        }
    }
}
