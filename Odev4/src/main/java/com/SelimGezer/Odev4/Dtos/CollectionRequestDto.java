package com.SelimGezer.Odev4.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CollectionRequestDto {

    private float collectionAmount;

    private Long userId;

    private Long debtId;

    private Date collectionDate;


}
