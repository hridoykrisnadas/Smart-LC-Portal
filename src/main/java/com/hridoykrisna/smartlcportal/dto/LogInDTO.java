package com.hridoykrisna.smartlcportal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LogInDTO {
    @NotBlank(message = "Must put the Username")
    private String username;
    @NotBlank(message = "Password is mandatory")
    private String password;

}
