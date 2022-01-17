package com.SelimGezer.Odev4.Controller;

import com.SelimGezer.Odev4.Dtos.LfrRequestDto;
import com.SelimGezer.Odev4.Dtos.LfrResponceDto;
import com.SelimGezer.Odev4.Services.LateFeeRateEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/lateFeeRates")
public class LateFeeRateController {

    private final LateFeeRateEntityService lateFeeRateEntityService;

    public LateFeeRateController(LateFeeRateEntityService lateFeeRateEntityService) {
        this.lateFeeRateEntityService = lateFeeRateEntityService;
    }

    @GetMapping()
    public List<LfrResponceDto> getAllLateFeeRates(){
        return lateFeeRateEntityService.getAllLateFeeRates();
    }

    @GetMapping("/{id}")
    public LfrResponceDto getLateFeeRateById(@PathVariable("id") Long id){
        return lateFeeRateEntityService.getLateFeeRateById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public LfrResponceDto addLateFeeRate(@RequestBody LfrRequestDto lfrRequestDto){
        return  lateFeeRateEntityService.addLateFeeRate(lfrRequestDto);
    }

    @PutMapping("/{id}")
    public LfrResponceDto updateLateFeeRateId(@PathVariable("id") Long id,@RequestBody LfrRequestDto lfrRequestDto){
        return lateFeeRateEntityService.updateLateFeeRateById(id,lfrRequestDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLateFeeRateById(@PathVariable("id") Long id){
        return lateFeeRateEntityService.deleteLateFeeRateById(id);
    }
}
