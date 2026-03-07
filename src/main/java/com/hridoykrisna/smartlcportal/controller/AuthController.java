package com.hridoykrisna.smartlcportal.controller;

import com.hridoykrisna.smartlcportal.dto.ResponseDTO;
import com.hridoykrisna.smartlcportal.dto.UserRegistrationDTO;
import com.hridoykrisna.smartlcportal.entity.AppUser;
import com.hridoykrisna.smartlcportal.service.AuthUserService;
import com.hridoykrisna.smartlcportal.util.ResponseBuilder;
import com.hridoykrisna.smartlcportal.util.URLConstrain;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(URLConstrain.AuthManagement.ROOT)
public class AuthController {
    private final AuthUserService authUserService;

    @PostMapping(URLConstrain.AuthManagement.REGISTRATION)
    public ResponseDTO appUserRegistration(@Valid @RequestBody UserRegistrationDTO registrationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseBuilder.getFailureError(bindingResult, "Binding Error");
        }
        return authUserService.registration(registrationDTO);
    }

}
