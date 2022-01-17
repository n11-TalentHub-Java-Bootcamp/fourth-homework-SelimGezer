package com.SelimGezer.Odev4.Mapper;

import com.SelimGezer.Odev4.Dtos.CollectionRequestDto;
import com.SelimGezer.Odev4.Dtos.CollectionResponceDto;
import com.SelimGezer.Odev4.Entities.Collection;
import com.SelimGezer.Odev4.Entities.Debt;
import com.SelimGezer.Odev4.Entities.User;

import java.util.Date;

public class CollectionMapper {

    public static Collection toEntity(CollectionRequestDto collectionRequestDto, User user, Debt debt, Date collectionDate){
        Collection collection=new Collection(user,debt, collectionRequestDto.getCollectionAmount(),collectionDate);
        return collection;
    }

    public static CollectionResponceDto toDto(Collection collection){
        CollectionResponceDto collectionResponceDto=new CollectionResponceDto(collection.getId(),collection.getUser(),collection.getDebt(),collection.getCollectionDate(),collection.getCollectionAmount());
        return collectionResponceDto;
    }

    public static Collection toEntity(CollectionResponceDto collectionResponceDto){
        Collection collection=new Collection(collectionResponceDto.getId(),collectionResponceDto.getUser(),collectionResponceDto.getDebt(), collectionResponceDto.getCollectionAmount(),collectionResponceDto.getCollectionDate());
        return collection;
    }

}
