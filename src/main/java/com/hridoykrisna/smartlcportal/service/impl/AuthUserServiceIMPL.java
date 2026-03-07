package com.hridoykrisna.smartlcportal.service.impl;

import com.hridoykrisna.smartlcportal.dto.ResponseDTO;
import com.hridoykrisna.smartlcportal.dto.UserRegistrationDTO;
import com.hridoykrisna.smartlcportal.entity.AppUser;
import com.hridoykrisna.smartlcportal.repository.APPUserRepo;
import com.hridoykrisna.smartlcportal.service.AuthUserService;
import com.hridoykrisna.smartlcportal.util.ResponseBuilder;
import io.jsonwebtoken.security.Password;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserServiceIMPL implements AuthUserService {
    private final APPUserRepo appUserRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public ResponseDTO registration(UserRegistrationDTO userRegistrationDTO) {
        AppUser appUser = modelMapper.map(userRegistrationDTO, AppUser.class);
        appUser.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        AppUser appUser1 = appUserRepo.save(appUser);
        if (appUser1 != null) {
            UserRegistrationDTO userRegistrationDTO1 = modelMapper.map(appUser1, UserRegistrationDTO.class);
            userRegistrationDTO1.setPassword("Secret Password");
            return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "User Registration Successful", userRegistrationDTO1);
        }
        return ResponseBuilder.getFailureError(HttpStatus.INTERNAL_SERVER_ERROR, "AppUser Not Found");
    }
}
