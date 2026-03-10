package com.hridoykrisna.smartlcportal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.NaturalId;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "APP_USER")
public class AppUser extends BaseModel{
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    @NaturalId
    private String username;
    @Column(nullable = false)
    private String password;
    private String role;
    private boolean isEnable = false;

}
