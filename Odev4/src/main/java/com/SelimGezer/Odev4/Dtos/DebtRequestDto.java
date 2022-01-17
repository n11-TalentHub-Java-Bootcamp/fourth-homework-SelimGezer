package com.SelimGezer.Odev4.Dtos;

import com.SelimGezer.Odev4.Entities.Debt;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebtRequestDto {

    @JsonIgnore
    private Long debtId;

    private Long userId;

    private String debtDescription;

    private float mainDebtAmount;
    private float remanderDebtAmount;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date maturityDate;

    @JsonIgnore
    private Debt connectedDebt;
}
