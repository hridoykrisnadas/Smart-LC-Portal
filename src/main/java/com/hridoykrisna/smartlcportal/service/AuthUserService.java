package com.hridoykrisna.smartlcportal.service;

import com.hridoykrisna.smartlcportal.dto.LogInDTO;
import com.hridoykrisna.smartlcportal.dto.ResponseDTO;
import com.hridoykrisna.smartlcportal.dto.UserRegistrationDTO;
import jakarta.validation.Valid;

public interface AuthUserService {
    ResponseDTO registration(UserRegistrationDTO userRegistrationDTO);

    ResponseDTO login(@Valid LogInDTO logInDTO);

    ResponseDTO verifyEmailToken(String token);
}
