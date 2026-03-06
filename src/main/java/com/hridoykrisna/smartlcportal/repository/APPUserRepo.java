package com.hridoykrisna.smartlcportal.repository;

import com.hridoykrisna.smartlcportal.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface APPUserRepo extends JpaRepository<AppUser,Integer> {

}
