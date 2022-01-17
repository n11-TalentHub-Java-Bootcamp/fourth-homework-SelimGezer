package com.SelimGezer.Odev4.Dao;

import com.SelimGezer.Odev4.Entities.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CollectionDao extends JpaRepository<Collection,Long> {

    List<Collection> findByCollectionDateBetween(Date firstDate, Date endDate);

    List<Collection> findByUserId(Long id);
}
