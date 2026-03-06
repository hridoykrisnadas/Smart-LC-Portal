package com.hridoykrisna.smartlcportal.repository;

import com.hridoykrisna.smartlcportal.entity.LCMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LCMasterRepo extends JpaRepository<LCMaster,Integer> {

}
