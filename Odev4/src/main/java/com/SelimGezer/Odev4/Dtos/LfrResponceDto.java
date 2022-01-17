package com.SelimGezer.Odev4.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
public class LfrResponceDto {
    @Temporal(TemporalType.DATE)
    private Date firstDate;
    @Temporal(TemporalType.DATE)
    private Date lastDate;
    private float rate;
}
