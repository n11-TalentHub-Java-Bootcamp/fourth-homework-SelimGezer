package com.SelimGezer.Odev4.Services;

import com.SelimGezer.Odev4.Dao.LateFeeRateDao;
import com.SelimGezer.Odev4.Dtos.LfrRequestDto;
import com.SelimGezer.Odev4.Dtos.LfrResponceDto;
import com.SelimGezer.Odev4.Entities.LateFeeRate;
import com.SelimGezer.Odev4.Exception.LfrNotFound;
import com.SelimGezer.Odev4.Mapper.LfrMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LateFeeRateEntityService {

    private final LateFeeRateDao lateFeeRateDao;


    public LateFeeRateEntityService(LateFeeRateDao lateFeeRateDao) {
        this.lateFeeRateDao = lateFeeRateDao;
    }

    public List<LfrResponceDto> getAllLateFeeRates() {
        return lateFeeRateDao.findAll().stream().map(lateFeeRate -> LfrMapper.toDto(lateFeeRate)).collect(Collectors.toList());
    }

    public LfrResponceDto getLateFeeRateById(Long id) {
        Optional<LateFeeRate> optionalLateFeeRate = lateFeeRateDao.findById(id);
        if(optionalLateFeeRate.isPresent()){
            return LfrMapper.toDto(optionalLateFeeRate.get());
        }else{
            throw new LfrNotFound(String.format("%d id sine sahip bir gecikme zammı bulunamadı!",id));
        }
    }

    public LfrResponceDto addLateFeeRate(LfrRequestDto lfrRequestDto) {
        LateFeeRate lateFeeRate=LfrMapper.toEntity(lfrRequestDto);
        LateFeeRate save = lateFeeRateDao.save(lateFeeRate);
        return LfrMapper.toDto(save);
    }

    public LfrResponceDto updateLateFeeRateById(Long id, LfrRequestDto lfrRequestDto) {
        Optional<LateFeeRate> optionalLateFeeRate = lateFeeRateDao.findById(id);
        if(optionalLateFeeRate.isPresent()){
            LateFeeRate lateFeeRate = LfrMapper.toEntity(lfrRequestDto);
            lateFeeRate.setId(id);
            LateFeeRate save = lateFeeRateDao.save(lateFeeRate);
            return LfrMapper.toDto(save);
        }else{
            throw new LfrNotFound(String.format("%d id sine sahip bir gecikme zammı bulunamadı!",id));
        }
    }

    public ResponseEntity<String> deleteLateFeeRateById(Long id) {
        Optional<LateFeeRate> optionalLateFeeRate = lateFeeRateDao.findById(id);
        if(optionalLateFeeRate.isPresent()){
            lateFeeRateDao.deleteById(id);
            return ResponseEntity.ok("Silme işlemi başarı ile gerçekleşti!");
        }else{
            throw new LfrNotFound(String.format("%d id sine sahip bir gecikme zammı bulunamadı!",id));
        }
    }
}
