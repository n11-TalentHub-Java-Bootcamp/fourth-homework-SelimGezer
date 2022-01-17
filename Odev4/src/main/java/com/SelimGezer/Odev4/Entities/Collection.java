package com.SelimGezer.Odev4.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Collection")
@Getter
@Setter
@NoArgsConstructor
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,cascade ={  CascadeType.DETACH})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "debt_id")
    Debt debt;

    @Temporal(TemporalType.DATE)
    private Date collectionDate;


    private float collectionAmount;

    public Collection(User user, Debt debt, float collectionAmount,Date collectionDate) {
        this.user = user;
        this.debt = debt;
        this.collectionAmount = collectionAmount;
        this.collectionDate=collectionDate;
    }
    public Collection(Long id,User user, Debt debt, float collectionAmount,Date collectionDate) {
        this.id=id;
        this.user = user;
        this.debt = debt;
        this.collectionAmount = collectionAmount;
        this.collectionDate=collectionDate;
    }
}
