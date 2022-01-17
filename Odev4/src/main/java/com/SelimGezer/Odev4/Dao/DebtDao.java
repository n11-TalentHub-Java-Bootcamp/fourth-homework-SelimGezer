package com.SelimGezer.Odev4.Dao;

import com.SelimGezer.Odev4.Entities.Debt;
import com.SelimGezer.Odev4.Enum.DeptType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DebtDao extends JpaRepository<Debt,Long> {

    List<Debt> findByUserId(Long userId);

    List<Debt> findByUserIdAndRemanderDebtAmountGreaterThan(Long userId,float remanderDebtAmount);

    @Query("select debt from Debt debt JOIN debt.user b where b.id=:userId and debt.maturityDate<:maturityDate ")
    List<Debt> findAllByUserIdAndMaturityDateAfter(@Param("userId") Long userId,@Param("maturityDate") Date maturityDate);

    @Query("select debt from Debt debt JOIN debt.user b where b.id=:userId and debt.maturityDate>:maturityDate ")
    List<Debt> findAllByUserIdMaturityDateBefore(@Param("userId") Long userId,@Param("maturityDate") Date maturityDate);

    @Query("SELECT sum(debt.mainDebtAmount) FROM Debt debt WHERE user_id=:userId")
    public int findSumAllMainDebt(@Param("userId") Long id);

    @Query("select debt from Debt debt JOIN debt.user b where b.id=:userId and debt.maturityDate<:maturityDate")
    List<Debt> findSumOverdue(@Param("userId") Long userId,@Param("maturityDate") Date maturityDate);

    List<Debt> findByUserIdAndDebtType(Long id, DeptType debtType);

    List<Debt> findByCreationDateBetween(Date firstDate, Date endDate);
}
