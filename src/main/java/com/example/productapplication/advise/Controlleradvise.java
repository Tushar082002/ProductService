package com.example.productapplication.advise;

import com.example.productapplication.dto.ErrorDTO;
import com.example.productapplication.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class Controlleradvise {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException(){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorcode("404");
        errorDTO.setErrormsg("Bad Request");

        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException(){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorcode("404");
        errorDTO.setErrormsg("Product Not Found");

        return ResponseEntity.badRequest().body(errorDTO);
    }

}
