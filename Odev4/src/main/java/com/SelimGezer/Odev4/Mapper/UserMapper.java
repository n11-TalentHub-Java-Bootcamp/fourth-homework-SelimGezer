package com.SelimGezer.Odev4.Mapper;

import com.SelimGezer.Odev4.Dtos.UserRequestDto;
import com.SelimGezer.Odev4.Dtos.UserResponceDto;
import com.SelimGezer.Odev4.Entities.User;

public class UserMapper {

    public static User toEntity(UserRequestDto userRequestDto){
        User user=new User(userRequestDto.getName(),userRequestDto.getSurname(),userRequestDto.getEmail(),userRequestDto.getPhoneNumber());
        return user;
    }

    public static User toEntity(UserResponceDto userResponceDto){
        User user=new User(userResponceDto.getName(),userResponceDto.getSurname(),userResponceDto.getEmail(),userResponceDto.getPhoneNumber());
        return user;
    }


    public static UserResponceDto toDto(User user){
        UserResponceDto userResponceDto=new UserResponceDto(user.getName(),user.getSurname(),user.getEmail(),user.getPhoneNumber());
        return userResponceDto;
    }
}
