package com.SelimGezer.Odev4.Mapper;

import com.SelimGezer.Odev4.Dtos.*;
import com.SelimGezer.Odev4.Entities.Debt;
import com.SelimGezer.Odev4.Entities.User;
import com.SelimGezer.Odev4.Enum.DeptType;

public class DebtMapper {

    public static Debt toEntity(UserResponceDto userById, DebtRequestDto debtRequestDto, DeptType deptType){
        User user=UserMapper.toEntity(userById);
        user.setId(debtRequestDto.getUserId());
        Debt debt=new Debt(user,debtRequestDto.getDebtDescription(), debtRequestDto.getMainDebtAmount(), debtRequestDto.getCreationDate(),debtRequestDto.getMaturityDate(),deptType,debtRequestDto.getConnectedDebt());
        return debt;
    }

    public static DebtRequestDto toReqDto(Debt debt){
        DebtRequestDto debtRequestDto=new DebtRequestDto(debt.getId(),debt.getUser().getId(),debt.getDebtDescription(),debt.getMainDebtAmount(), debt.getRemanderDebtAmount(),debt.getCreationDate(),debt.getMaturityDate(),debt.getConnectedDebt());
        return debtRequestDto;
    }

    public static DebtResponceDto toResDto(Debt debt){
        DebtResponceDto debtResponceDto=new DebtResponceDto(debt.getUser(),debt.getDebtDescription(),debt.getMainDebtAmount(), debt.getRemanderDebtAmount(), debt.getCreationDate(),debt.getMaturityDate(),debt.getDebtType());
        return debtResponceDto;
    }

    public static DebtAddResponceDto toAddDto(Debt debt){
        DebtAddResponceDto debtAddResponceDto=new DebtAddResponceDto(debt.getId(),debt.getUser(),debt.getDebtDescription(),debt.getMainDebtAmount(),debt.getCreationDate(),debt.getMaturityDate(),debt.getDebtType(),debt.getConnectedDebt());
        return debtAddResponceDto;
    }
}
