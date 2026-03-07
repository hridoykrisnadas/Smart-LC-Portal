package com.hridoykrisna.smartlcportal.controller;

import com.hridoykrisna.smartlcportal.dto.ResponseDTO;
import com.hridoykrisna.smartlcportal.util.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class WelcomeController {
    @GetMapping({"/", ""})
    public ResponseDTO index() {
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK,"Welcome to Smart LC Portal");
    }
}
