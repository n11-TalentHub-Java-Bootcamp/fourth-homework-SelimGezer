package com.SelimGezer.Odev4.Dtos;

import com.SelimGezer.Odev4.Entities.Debt;
import com.SelimGezer.Odev4.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@Data
@AllArgsConstructor
public class CollectionResponceDto {
    private Long id;

    private User user;

    Debt debt;

    private Date collectionDate;

    private float collectionAmount;
}
