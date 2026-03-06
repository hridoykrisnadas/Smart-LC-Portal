package com.example.smartlcprotal.repository;

import com.example.smartlcprotal.entity.LCMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LCMasterRepo extends JpaRepository<LCMaster,Integer> {

}
