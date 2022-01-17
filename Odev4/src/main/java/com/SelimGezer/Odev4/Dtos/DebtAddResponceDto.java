package com.SelimGezer.Odev4.Dtos;

import com.SelimGezer.Odev4.Entities.Debt;
import com.SelimGezer.Odev4.Entities.User;
import com.SelimGezer.Odev4.Enum.DeptType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
public class DebtAddResponceDto {

    @JsonIgnore
    private Long id;

    private User user;

    private String debtDescription;

    private float mainDebtAmount;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date maturityDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private DeptType debtType;

    private Debt connectedDebt;

}
