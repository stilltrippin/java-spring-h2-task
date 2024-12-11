package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PriceController {

    @Autowired
    private  PriceService priceService;

    @GetMapping("/api/prices")
    public ResponseEntity<PriceResponse> getPriceDetails(@RequestBody PriceDTO price) {
        PriceResponse resultPrice = priceService.getPriceForProduct(price.getDate(), price.getBrandId(), price.getProductId());
        if (resultPrice == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(resultPrice, HttpStatus.OK);
    }




}
