package com.sysco.sampleService.Stas.controller;

import com.sysco.sampleService.Kirill.service.ProductService;
import com.sysco.sampleService.Stas.errors.HomeError;
import com.sysco.sampleService.Stas.model.HomeApiResponse;
import com.sysco.sampleService.Stas.model.HomeEnvelopeResponse;
import com.sysco.sampleService.Stas.service.HomeService;
import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HomeControllerTest {

    private final HomeService homeService = mock(HomeService.class);

    HomeController homeController = new HomeController(homeService);

    @Test
    public void test_HomeController_success(){

        HomeEnvelopeResponse homeEnvelopeResponse = HomeEnvelopeResponse.builder()
                .sellerID("CABL")
                .productID("102")
                .data(HomeApiResponse.builder()
                        .name("Grape Jelly")
                        .L1("Condiments")
                        .L2("Jelly/Jam")
                        .L3("Grape")
                        .build())
                .error(List.of())
                .build();

        when(homeService.putInfoToDao("CABL","102")).thenReturn(homeEnvelopeResponse);

        HomeEnvelopeResponse response = homeController.getInfo("CABL", "102");

        assertNotNull(response);

        assertTrue(response.getError().isEmpty());

        assertEquals("CABL",response.getSellerID());
        assertEquals("102", response.getProductID());
        assertEquals("Grape Jelly", response.getData().getName());
        assertEquals("Condiments", response.getData().getL1());
        assertEquals("Jelly/Jam", response.getData().getL2());
        assertEquals("Grape", response.getData().getL3());
    }

    @Test
    public void test_HomeController_fail(){

        HomeEnvelopeResponse homeEnvelopeResponse = HomeEnvelopeResponse.builder()
                .sellerID("CABL")
                .productID("102")
                .data(null)
                .error(List.of(new HomeError(500, "Error")))
                .build();

        when(homeService.putInfoToDao("CABL", "102")).thenReturn(homeEnvelopeResponse);

        HomeEnvelopeResponse response = homeController.getInfo("CABL", "102");

        assertNotNull(response);

        assertEquals("CABL", response.getSellerID());
        assertEquals("102", response.getProductID());
        assertNull(response.getData());
        assertEquals(500, response.getError().get(0).getCode());
        assertEquals("Error", response.getError().get(0).getMassage());
    }

}
