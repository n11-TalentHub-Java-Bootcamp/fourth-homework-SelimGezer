package com.SelimGezer.Odev4.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LateFeeRate")
@Data
@NoArgsConstructor
public class LateFeeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date firstDate;
    @Temporal(TemporalType.DATE)
    private Date lastDate;

    private float rate;

    public LateFeeRate(Date firstDate, Date lastDate,float rate) {
        this.firstDate = firstDate;
        this.lastDate = lastDate;
        this.rate=rate;
    }
}
