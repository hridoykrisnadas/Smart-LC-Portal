package com.hridoykrisna.smartlcportal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.catalina.User;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "CUSTOMER_PROFILE")
public class CustomerProfile extends BaseModel{
    private String companyName;
    private String tradeLicenseNo;
    private String docVerificationStatus;

    @OneToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;
}
