package com.hridoykrisna.smartlcportal.repository;

import com.hridoykrisna.smartlcportal.entity.LCAmendment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LCAmendmentRepo extends JpaRepository<LCAmendment,Integer> {
}
