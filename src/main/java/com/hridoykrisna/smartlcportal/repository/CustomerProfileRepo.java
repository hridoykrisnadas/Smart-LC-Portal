package com.hridoykrisna.smartlcportal.repository;

import com.hridoykrisna.smartlcportal.entity.CustomerProfile;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerProfileRepo extends JpaRepositoryImplementation<CustomerProfile,Integer> {

}
