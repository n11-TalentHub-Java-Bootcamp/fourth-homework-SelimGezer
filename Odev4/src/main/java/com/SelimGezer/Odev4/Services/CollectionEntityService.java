package com.SelimGezer.Odev4.Services;

import com.SelimGezer.Odev4.Dao.CollectionDao;
import com.SelimGezer.Odev4.Dtos.CollectionRequestDto;
import com.SelimGezer.Odev4.Dtos.CollectionResponceDto;
import com.SelimGezer.Odev4.Dtos.DebtAddResponceDto;
import com.SelimGezer.Odev4.Dtos.DebtRequestDto;
import com.SelimGezer.Odev4.Entities.Collection;
import com.SelimGezer.Odev4.Entities.Debt;
import com.SelimGezer.Odev4.Entities.User;
import com.SelimGezer.Odev4.Enum.DeptType;
import com.SelimGezer.Odev4.Mapper.CollectionMapper;
import com.SelimGezer.Odev4.Util.Constants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectionEntityService {

    private final CollectionDao collectionDao;
    private final UserEntityService userEntityService;
    private final DebtEntityService debtEntityService;

    public CollectionEntityService(CollectionDao collectionDao, UserEntityService userEntityService, DebtEntityService debtEntityService) {
        this.collectionDao = collectionDao;
        this.userEntityService = userEntityService;
        this.debtEntityService = debtEntityService;
    }


    @Transactional()
    public String addCollection(CollectionRequestDto collectionRequestDto) {
        User checkUser = userEntityService.checkUserById(collectionRequestDto.getUserId());
        Debt checkDebt = debtEntityService.checkDebtById(collectionRequestDto.getDebtId());

        Date dateNow = null;
        try {
            dateNow = Constants.simpleDateFormat.parse(LocalDate.now().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(checkDebt.getMaturityDate().after(dateNow)){//tarihi geçmemişse
            Collection collection=new Collection(checkUser,checkDebt,collectionRequestDto.getCollectionAmount(),dateNow);
            String s = debtEntityService.updateDebt(checkDebt.getId(), collectionRequestDto.getCollectionAmount());
            collectionDao.save(collection);
            return "İşlem tamamlandı!";
        }else{//geçmişse

            try {
                long day=debtEntityService.dateDifference(checkDebt.getMaturityDate(),dateNow);
                float lateFeeCalculate = debtEntityService.lateFeeCalculate(checkDebt.getMainDebtAmount(), day, Constants.BEFORE_2018);
                DebtRequestDto newDebtDto = new DebtRequestDto();
                newDebtDto.setUserId(checkUser.getId());
                newDebtDto.setMainDebtAmount(lateFeeCalculate);
                newDebtDto.setRemanderDebtAmount(lateFeeCalculate);
                newDebtDto.setConnectedDebt(checkDebt);
                newDebtDto.setCreationDate(dateNow);
                newDebtDto.setMaturityDate(dateNow);
                newDebtDto.setDebtDescription(checkDebt.getDebtDescription());
                //newDebtDto.setConnectedDebt(checkDebt);

                DebtAddResponceDto debtAddResponceDto = debtEntityService.addDebt(newDebtDto, DeptType.LATE_FEE);

                debtEntityService.updateDebt(debtAddResponceDto.getId(), lateFeeCalculate);

                debtEntityService.updateDebt(checkDebt.getId(), collectionRequestDto.getCollectionAmount());

                Collection collection1=new Collection(checkUser,checkDebt, collectionRequestDto.getCollectionAmount(), dateNow);
                Collection save1 = collectionDao.save(collection1);

                //Debt newDebt = DebtMapper.toEntity(UserMapper.toDto(checkUser), newDebtDto, DeptType.LATE_FEE);
                Debt checkDebt2 = debtEntityService.checkDebtById(debtAddResponceDto.getId());

                Collection collection2=new Collection(checkUser,checkDebt2, lateFeeCalculate, dateNow);
                Collection save2 = collectionDao.save(collection2);

                return "İşlem tamamlandı!";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("Beklenmeyen bir hata oluştu!");
    }

    public List<CollectionResponceDto> getAllCollectionBetweenDate(Date firstDate, Date endDate) {
            return collectionDao.findByCollectionDateBetween(firstDate,endDate).stream().map(collection -> CollectionMapper.toDto(collection)).collect(Collectors.toList());
    }

    public List<CollectionResponceDto> getAllCollectionByUserId(Long id) {
        userEntityService.checkUserById(id);
        return collectionDao.findByUserId(id).stream().map(collection -> CollectionMapper.toDto(collection)).collect(Collectors.toList());
    }

    public String getAllLateFeeAmountByUserId(Long id) {
        List<Collection> allCollectionByUserId = getAllCollectionByUserId(id).stream().map(collectionResponceDto -> CollectionMapper.toEntity(collectionResponceDto)).collect(Collectors.toList());
        float totalLateFeeAmount=0;
        for (Collection collection: allCollectionByUserId) {
            Debt debt=collection.getDebt();
           if(debt.getConnectedDebt() != null && debt.getRemanderDebtAmount()==0){
               totalLateFeeAmount+=debt.getMainDebtAmount();
           }
        }

        return ""+totalLateFeeAmount;
    }
}
