package com.SelimGezer.Odev4.Dtos;

import com.SelimGezer.Odev4.Entities.User;
import com.SelimGezer.Odev4.Enum.DeptType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
public class DebtResponceDto {
    private User user;

    private String debtDescription;

    private float mainDebtAmount;
    private float remanderDebtAmount;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date maturityDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private DeptType debtType;

  /*  @OneToMany(mappedBy = "debt")
    List<Collection> collectionList;*/

}
