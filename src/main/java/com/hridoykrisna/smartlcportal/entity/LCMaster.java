package com.example.smartlcprotal.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "LC_MASTER")
public class LCMaster extends BaseModel{
    @Column(unique = true, nullable = false)
    private String lcNumber;
    private String beneficiaryName;
    private Double lcAmount;
    private Double marginPercentage;
    private Double marginAmount;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private LocalDate latestDateOfShipment;
    private String status; // OPEN, AMENDED, CLOSED

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerProfile customerProfile;
}
