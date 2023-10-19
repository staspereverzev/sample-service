package com.sysco.sampleService.Stas.model;

import com.sysco.sampleService.Stas.errors.HomeError;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class HomeEnvelopeResponse {
    private String sellerID;
    private String productID;
    private HomeApiResponse data;
    private List<HomeError> error;

    public static HomeEnvelopeResponse generateResponse(String sellerID, String productID, HomeApiResponse homeApiResponse, List<HomeError> homeError){
        //Response response =  method call
        return HomeEnvelopeResponse.builder()
                .sellerID(sellerID)
                .productID(productID)
                .data(homeApiResponse)
                .error(homeError)
                .build();
    }
}
