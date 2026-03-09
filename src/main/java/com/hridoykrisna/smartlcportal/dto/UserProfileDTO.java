package com.hridoykrisna.smartlcportal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserProfileDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String role;
}
