package com.sysco.sampleService.Stas.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class HomeApiResponse {

    private String name;
    private String L1;
    private String L2;
    private String L3;
}
