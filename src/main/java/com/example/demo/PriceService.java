package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepo;

    public PriceResponse getPriceForProduct(LocalDateTime date,
                                 Long brandId,
                                 Long productId) {
        List <Price> priceList =  priceRepo.findFirstByDateAndProductIdAndBrandId(date,brandId,productId);
        if (priceList.isEmpty()) {
            return null;
        } else {
            Price price = priceList.stream()
                    .max(Comparator.comparingInt(Price::getPriority)).orElse(null);
            return new PriceResponse(price);
        }
    }
}
