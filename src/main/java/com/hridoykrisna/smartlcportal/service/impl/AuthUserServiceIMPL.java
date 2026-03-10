package com.hridoykrisna.smartlcportal.service.impl;

import com.hridoykrisna.smartlcportal.auth.CustomUserDetailsService;
import com.hridoykrisna.smartlcportal.auth.JWTService;
import com.hridoykrisna.smartlcportal.dto.LogInDTO;
import com.hridoykrisna.smartlcportal.dto.ResponseDTO;
import com.hridoykrisna.smartlcportal.dto.UserProfileDTO;
import com.hridoykrisna.smartlcportal.dto.UserRegistrationDTO;
import com.hridoykrisna.smartlcportal.entity.AppUser;
import com.hridoykrisna.smartlcportal.entity.VerificationToken;
import com.hridoykrisna.smartlcportal.repository.APPUserRepo;
import com.hridoykrisna.smartlcportal.repository.VerificationTokenRepo;
import com.hridoykrisna.smartlcportal.service.AuthUserService;
import com.hridoykrisna.smartlcportal.service.EmailService;
import com.hridoykrisna.smartlcportal.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

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

    private final EmailService emailService;
    private final VerificationTokenRepo verificationTokenRepo;


    @Override
    public ResponseDTO registration(UserRegistrationDTO userRegistrationDTO) {

        if (appUserRepo.existsByUsername(userRegistrationDTO.getUsername()))
            return ResponseBuilder.getFailureError(HttpStatus.BAD_REQUEST, "Username is already in use");

        if (appUserRepo.existsByEmail(userRegistrationDTO.getEmail()))
            return ResponseBuilder.getFailureError(HttpStatus.BAD_REQUEST, "Email is already in use");

        AppUser appUser = modelMapper.map(userRegistrationDTO, AppUser.class);
        appUser.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        AppUser savedUser = appUserRepo.save(appUser);

        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = new VerificationToken(token, appUser);
        verificationTokenRepo.save(verificationToken);

        emailService.sendVerificationEmail(savedUser.getEmail(), verificationToken.getToken());

        if (savedUser != null) {
            UserProfileDTO userProfileDTO = modelMapper.map(savedUser, UserProfileDTO.class);
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
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(logInDTO.getUsername());

            String jwt = jwtService.generateToken(userDetails);
            HashMap<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token", jwt);

            return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Login Successful", tokenMap);
        } catch (DisabledException e) {
            return ResponseBuilder.getFailureError(HttpStatus.FORBIDDEN, "Your account is not verified! Please check your email to verify.");
        } catch (BadCredentialsException e) {
            return ResponseBuilder.getFailureError(HttpStatus.BAD_REQUEST, "Bad Credentials");
        } catch (Exception e) {
            return ResponseBuilder.getFailureError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @Override
    public ResponseDTO verifyEmailToken(String token) {
        VerificationToken verificationToken = verificationTokenRepo.findByToken(token);
        if (verificationToken == null) {
            return ResponseBuilder.getFailureError(HttpStatus.NOT_FOUND, "Verification Token Not Found");
        }
        AppUser appUser = verificationToken.getAppUser();

        if (appUser.isEnable()) {
            return ResponseBuilder.getSuccessResponse(HttpStatus.ALREADY_REPORTED, "The user already verified, please login again");
        }

        Calendar calendar = Calendar.getInstance();
        if (verificationToken.getExpiryDate().before(calendar.getTime())) {
            verificationTokenRepo.delete(verificationToken);
            return ResponseBuilder.getFailureError(HttpStatus.UNAUTHORIZED, "Verification Token Expired");
        }
        appUser.setEnable(true);
        appUserRepo.save(appUser);

        return ResponseBuilder.getSuccessResponse(HttpStatus.ACCEPTED, "Verification Successful");
    }
}
