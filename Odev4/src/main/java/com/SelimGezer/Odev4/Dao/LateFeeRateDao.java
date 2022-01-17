package com.SelimGezer.Odev4.Dao;

import com.SelimGezer.Odev4.Entities.LateFeeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LateFeeRateDao extends JpaRepository<LateFeeRate,Long> {
}
