package com.sysco.sampleService.Stas.service;

import com.sysco.sampleService.Stas.dao.HomeDao;
import com.sysco.sampleService.Stas.errors.HomeError;
import com.sysco.sampleService.Stas.errors.SellerIDProductID;
import com.sysco.sampleService.Stas.model.HomeApiResponse;
import com.sysco.sampleService.Stas.model.HomeEnvelopeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import static java.lang.String.format;
import java.util.List;

@Service
public class HomeService {

    private HomeDao homeDao;


    public HomeService(HomeDao homeDao) {
        this.homeDao = homeDao;
    }

    public HomeEnvelopeResponse putInfoToDao(String sellerID, String productID){
        HomeApiResponse homeApiResponse = homeDao.getProductFromDB(sellerID, productID); // != null

        if (homeApiResponse == null){
            SellerIDProductID sellerIDProductID = new SellerIDProductID(sellerID, productID);
            HomeError homeError = new HomeError(HttpStatus.NOT_FOUND.value(),format("Product data <%s>", sellerIDProductID));
            return HomeEnvelopeResponse.generateResponse(sellerID,productID,homeApiResponse, List.of(homeError));
        }
        return HomeEnvelopeResponse.generateResponse(sellerID, productID, homeApiResponse, List.of());
    }
}
