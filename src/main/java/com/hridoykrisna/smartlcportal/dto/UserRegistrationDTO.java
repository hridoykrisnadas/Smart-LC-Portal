package com.hridoykrisna.smartlcportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String id;
    @NotBlank(message = "First Name Mandatory")
    private String firstName;
    @NotBlank(message = "Last Name Mandatory")
    private String lastName;
    @NotBlank(message = "Must be put the Email")
    private String email;
    @NotBlank(message = "Must put the Username")
    private String username;
    @NotBlank(message = "Password is mandatory")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotBlank(message = "Must put the ROLE")
    private String role;
}
