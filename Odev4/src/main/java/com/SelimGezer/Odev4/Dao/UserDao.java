package com.SelimGezer.Odev4.Dao;

import com.SelimGezer.Odev4.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
}
