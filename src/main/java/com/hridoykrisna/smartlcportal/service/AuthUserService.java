package com.hridoykrisna.smartlcportal.service;

import com.hridoykrisna.smartlcportal.dto.ResponseDTO;
import com.hridoykrisna.smartlcportal.dto.UserRegistrationDTO;

public interface AuthUserService {
    ResponseDTO registration(UserRegistrationDTO userRegistrationDTO);
}
