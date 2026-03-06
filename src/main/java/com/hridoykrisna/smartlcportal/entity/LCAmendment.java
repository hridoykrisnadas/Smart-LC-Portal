package com.example.smartlcprotal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "LC_AMENDMENT")
public class LCAmendment extends BaseModel{
    private LocalDate amendmentDate;
    private Double amendmentAmount;
    private Double amendmentCharge;
    private String amendmentDescription;

    @ManyToOne
    @JoinColumn(name = "lc_master_id")
    private LCMaster lcMaster;
}
