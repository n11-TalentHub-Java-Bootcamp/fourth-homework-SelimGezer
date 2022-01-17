package com.SelimGezer.Odev4.Mapper;

import com.SelimGezer.Odev4.Dtos.LfrRequestDto;
import com.SelimGezer.Odev4.Dtos.LfrResponceDto;
import com.SelimGezer.Odev4.Entities.LateFeeRate;

public class LfrMapper {

    public static LateFeeRate toEntity(LfrRequestDto lfrRequestDto){
        LateFeeRate lateFeeRate=new LateFeeRate(lfrRequestDto.getFirstDate(),lfrRequestDto.getLastDate(),lfrRequestDto.getRate());
        return lateFeeRate;
    }

    public static LfrResponceDto toDto(LateFeeRate lateFeeRate){
        LfrResponceDto lfrResponceDto=new LfrResponceDto(lateFeeRate.getFirstDate(),lateFeeRate.getLastDate(),lateFeeRate.getRate());
        return lfrResponceDto;
    }
}
