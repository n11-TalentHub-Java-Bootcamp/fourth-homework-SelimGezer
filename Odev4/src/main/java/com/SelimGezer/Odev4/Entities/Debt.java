package com.SelimGezer.Odev4.Entities;

import com.SelimGezer.Odev4.Enum.DeptType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Dept")
@Data
@NoArgsConstructor
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = { CascadeType.MERGE })
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 50)
    private String debtDescription;

    private float mainDebtAmount;
    private float remanderDebtAmount;

    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Temporal(TemporalType.DATE)
    private Date maturityDate;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private DeptType debtType;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    @JoinColumn(name = "connectedDebt")
    Debt connectedDebt;

    @OneToMany(mappedBy = "debt",cascade = CascadeType.ALL)
    List<Collection> collectionList;

    public Debt(User user, String debtDescription, float mainDebtAmount, Date creationDate, Date maturityDate, DeptType debtType,Debt connectedDebt) {
        this.user = user;
        this.debtDescription = debtDescription;
        this.mainDebtAmount =mainDebtAmount ;
        this.remanderDebtAmount=mainDebtAmount;
        this.creationDate = creationDate;
        this.maturityDate = maturityDate;
        this.debtType = debtType;
        this.connectedDebt=connectedDebt;
    }

    public Debt(Long id,User user, String debtDescription, float mainDebtAmount, Date creationDate, Date maturityDate, DeptType debtType,Debt connectedDebt) {
        this.id=id;
        this.user = user;
        this.debtDescription = debtDescription;
        this.mainDebtAmount =mainDebtAmount ;
        this.remanderDebtAmount=mainDebtAmount;
        this.creationDate = creationDate;
        this.maturityDate = maturityDate;
        this.debtType = debtType;
        this.connectedDebt=connectedDebt;
    }
}
