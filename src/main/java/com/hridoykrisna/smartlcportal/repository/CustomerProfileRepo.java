package com.example.smartlcprotal.repository;

import com.example.smartlcprotal.entity.CustomerProfile;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerProfileRepo extends JpaRepositoryImplementation<CustomerProfile,Integer> {

}
