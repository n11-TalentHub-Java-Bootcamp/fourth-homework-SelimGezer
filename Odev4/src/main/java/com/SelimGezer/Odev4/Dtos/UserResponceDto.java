package com.SelimGezer.Odev4.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponceDto {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
}
