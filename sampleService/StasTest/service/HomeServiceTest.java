package com.sysco.sampleService.Stas.service;

import com.sysco.sampleService.Stas.dao.HomeDao;
import com.sysco.sampleService.Stas.model.HomeApiResponse;
import com.sysco.sampleService.Stas.model.HomeEnvelopeResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HomeServiceTest {

    private final HomeDao homeDao = mock(HomeDao.class);

    HomeService homeService = new HomeService(homeDao);

    @Test
    public void test_HomeService_success(){

        HomeApiResponse homeApiResponse = HomeApiResponse.builder()
                .name("Grape Jelly")
                .L1("Condiments")
                .L2("Jelly/Jam")
                .L3("Grape")
                .build();

        when(homeDao.getProductFromDB("CABL", "102")).thenReturn(homeApiResponse);

        HomeEnvelopeResponse response = homeService.putInfoToDao("CABL", "102");

        System.out.println(response.getData().getName());

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
    public void test_HomeService_fail(){

        when(homeDao.getProductFromDB("CABL", "102")).thenReturn(null);

        HomeEnvelopeResponse response = homeService.putInfoToDao("CABL", "102");

        assertNotNull(response);

        assertEquals("CABL", response.getSellerID());
        assertEquals("102", response.getProductID());
        assertNull(response.getData());
        assertEquals(404, response.getError().get(0).getCode());
        assertEquals("Product data <SellerIDProductID[sellerID=CABL, productID=102]>", response.getError().get(0).getMassage());
    }
}
