package com.example.smartlcprotal.repository;

import com.example.smartlcprotal.entity.LCAmendment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LCAmendmentRepo extends JpaRepository<LCAmendment,Integer> {
}
