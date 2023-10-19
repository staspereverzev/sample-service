package com.sysco.sampleService.Stas.controller;

import com.sysco.sampleService.Stas.model.HomeApiResponse;
import com.sysco.sampleService.Stas.model.HomeEnvelopeResponse;
import com.sysco.sampleService.Stas.service.HomeService;
import com.sysco.sampleService.Stas.validation.ProductIDValidation;
import com.sysco.sampleService.Stas.validation.SellerIDValidation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@Validated
@RestController
public class HomeController {

    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/sellerId/{sellerID}/products/{productID}/taxonomy/home")
    public HomeEnvelopeResponse getInfo(@PathVariable @SellerIDValidation String sellerID, @PathVariable @ProductIDValidation String productID){
        return homeService.putInfoToDao(sellerID, productID);
    }
}
