package com.hridoykrisna.smartlcportal.service.impl;

import com.hridoykrisna.smartlcportal.auth.CustomUserDetailsService;
import com.hridoykrisna.smartlcportal.auth.JWTService;
import com.hridoykrisna.smartlcportal.dto.LogInDTO;
import com.hridoykrisna.smartlcportal.dto.ResponseDTO;
import com.hridoykrisna.smartlcportal.dto.UserProfileDTO;
import com.hridoykrisna.smartlcportal.dto.UserRegistrationDTO;
import com.hridoykrisna.smartlcportal.entity.AppUser;
import com.hridoykrisna.smartlcportal.repository.APPUserRepo;
import com.hridoykrisna.smartlcportal.service.AuthUserService;
import com.hridoykrisna.smartlcportal.util.ResponseBuilder;
import io.jsonwebtoken.security.Password;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthUserServiceIMPL implements AuthUserService {
    private final APPUserRepo appUserRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    //    Authentication
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JWTService jwtService;


    @Override
    public ResponseDTO registration(UserRegistrationDTO userRegistrationDTO) {

        if (appUserRepo.existsByUsername(userRegistrationDTO.getUsername()))
            return ResponseBuilder.getFailureError(HttpStatus.BAD_REQUEST, "Username is already in use");

        if (appUserRepo.existsByEmail(userRegistrationDTO.getEmail()))
            return ResponseBuilder.getFailureError(HttpStatus.BAD_REQUEST, "Email is already in use");

        AppUser appUser = modelMapper.map(userRegistrationDTO, AppUser.class);
        appUser.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        AppUser appUser1 = appUserRepo.save(appUser);
        if (appUser1 != null) {
            UserProfileDTO userProfileDTO = modelMapper.map(appUser1, UserProfileDTO.class);
            return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "User Registration Successful", userProfileDTO);
        }
        return ResponseBuilder.getFailureError(HttpStatus.INTERNAL_SERVER_ERROR, "AppUser Not Found");
    }

    @Override
    public ResponseDTO login(LogInDTO logInDTO) {
        try {

            authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(logInDTO.getUsername(), logInDTO.getPassword())
                    );
            UserDetails userDetails =  customUserDetailsService.loadUserByUsername(logInDTO.getUsername());

            String jwt = jwtService.generateToken(userDetails);
            HashMap<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token", jwt);

            return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Login Successful", tokenMap);
        } catch (Exception e) {
            return ResponseBuilder.getFailureError(HttpStatus.UNAUTHORIZED, "Login Failed");
        }
    }
}
