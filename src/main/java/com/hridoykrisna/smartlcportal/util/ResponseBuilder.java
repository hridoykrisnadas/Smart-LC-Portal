package com.hridoykrisna.smartlcportal.util;

import com.hridoykrisna.smartlcportal.dto.ErrorResponseDTO;
import com.hridoykrisna.smartlcportal.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

public class ResponseBuilder {
    private ResponseBuilder() {
    }

    private static List<ErrorResponseDTO> getCustomError(BindingResult bindingResult) {
        List<ErrorResponseDTO> errorResponseDTOList = new ArrayList<>();
        bindingResult.getFieldErrors().forEach((fieldError) -> {
            ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
            errorResponseDTOList.add(errorResponseDTO);
        });
        return errorResponseDTOList;
    }

    public static ResponseDTO getFailureError(BindingResult bindingResult, String message) {
        return ResponseDTO.builder()
                .timestamp(System.currentTimeMillis())
                .message(message)
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .errorResponseDTOList(getCustomError(bindingResult))
                .build();
    }

    public static ResponseDTO getFailureError(HttpStatus status, String message) {
        return ResponseDTO.builder()
                .timestamp(System.currentTimeMillis())
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .message(message)
                .build();
    }

    public static ResponseDTO getSuccessResponse(HttpStatus status, String message) {
        return ResponseDTO.builder()
                .timestamp(System.currentTimeMillis())
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .message(message)
                .build();
    }

    public static ResponseDTO getSuccessResponse(HttpStatus status, String message, Object object) {
        return ResponseDTO.builder()
                .timestamp(System.currentTimeMillis())
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .message(message)
                .content(object)
                .build();
    }
    public static ResponseDTO getSuccessResponse(HttpStatus status, String message, Object object, int numberOfElements) {
        return ResponseDTO.builder()
                .timestamp(System.currentTimeMillis())
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .message(message)
                .numberOfElements(numberOfElements)
                .content(object)
                .build();
    }
    public static ResponseDTO getSuccessResponse(HttpStatus status, String message, Object object,int numberOfElements, int rowCount) {
        return ResponseDTO.builder()
                .timestamp(System.currentTimeMillis())
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .rowCount(rowCount)
                .numberOfElements(numberOfElements)
                .message(message)
                .content(object)
                .build();
    }
}
