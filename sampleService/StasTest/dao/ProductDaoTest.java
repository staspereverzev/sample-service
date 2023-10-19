package com.sysco.sampleService.Stas.dao;

import com.sysco.sampleService.Stas.model.HomeApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

public class ProductDaoTest {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate = mock(NamedParameterJdbcTemplate.class);

    HomeDao homeDao = new HomeDao(namedParameterJdbcTemplate);



    @Test
    public void test_getProductFromDB_success() {

        HomeApiResponse homeApiResponse = HomeApiResponse.builder()
                .name("Grape Jelly")
                .L1("Condiments")
                .L2("Jelly/Jam")
                .L3("Grape")
                .build();

        when(namedParameterJdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class))).thenReturn(homeApiResponse);

        HomeApiResponse response = homeDao.getProductFromDB("CABL", "102");

        assertNotNull(response);

        assertEquals("Grape Jelly", response.getName());
        assertEquals("Condiments", response.getL1());
        assertEquals("Jelly/Jam", response.getL2());
        assertEquals("Grape", response.getL3());

    }


    @Test
    public void test_getProductFromDB_fail(){
        when(namedParameterJdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class), any(RowMapper.class)))
                .thenThrow(EmptyResultDataAccessException.class);

        HomeApiResponse response = homeDao.getProductFromDB("CABL", "102");

        assertNull(response);
    }


}
