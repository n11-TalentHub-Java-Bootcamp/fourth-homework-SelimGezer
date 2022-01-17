package com.SelimGezer.Odev4.Controller;

import com.SelimGezer.Odev4.Dtos.DebtAddResponceDto;
import com.SelimGezer.Odev4.Dtos.DebtRequestDto;
import com.SelimGezer.Odev4.Enum.DeptType;
import com.SelimGezer.Odev4.Services.DebtEntityService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/debts")
public class DebtController {

    private final DebtEntityService debtEntityService;

    public DebtController(DebtEntityService debtEntityService) {
        this.debtEntityService = debtEntityService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public DebtAddResponceDto addDebt(@RequestBody DebtRequestDto debtRequestDto){
        return debtEntityService.addDebt(debtRequestDto, DeptType.NORMAL);
    }

    @GetMapping()
    public List<DebtAddResponceDto> getAllDebt(){
        return debtEntityService.getAllDebt();
    }

    @GetMapping("/debt")
    public List<DebtAddResponceDto>  getAllDebtBetweenDate(@RequestParam("firstDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date firstDate,
                                             @RequestParam("endDate")   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate){
       return debtEntityService.getAllDebtBetweenDate(firstDate,endDate);
    }

    @GetMapping("/debt/{id}")
    public List<DebtAddResponceDto> getAllDebtByUserId(@PathVariable("id") Long id){
        return debtEntityService.getAllDebtByUserId(id);
    }

    @GetMapping("/debt/{id}/date")
    public List<DebtAddResponceDto> getAllDebtByUserIdAndMaturityDate(@PathVariable("id") Long id,@RequestParam("overdue") Boolean state){
        return debtEntityService.getAllDebtByUserIdAndMaturityDate(id,state);
    }

    @GetMapping("/debt/{id}/totalDebtAmount")
    public int getTotalDebtAmount(@PathVariable("id") Long id){
        return debtEntityService.getTotalDebtAmount(id);
    }

    @GetMapping("/debt/{id}/totalOverdueAmount")
    public int getTotalOverdueAmount(@PathVariable("id") Long id){
        return debtEntityService.getTotalOverdueAmount(id);
    }

    @GetMapping("/debt/{id}/totalLateFeeAmount")
    public float getTotalLatefeeAmount(@PathVariable("id") Long id, @RequestParam("paymentDate")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date paymentDate) throws ParseException {
        System.out.println(paymentDate);
        return debtEntityService.getTotalLatefeeAmount(id,paymentDate);
    }
}
