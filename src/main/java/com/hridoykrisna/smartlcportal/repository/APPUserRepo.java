package com.example.smartlcprotal.repository;

import com.example.smartlcprotal.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface APPUserRepo extends JpaRepository<AppUser,Integer> {

}
