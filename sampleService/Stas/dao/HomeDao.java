package com.sysco.sampleService.Stas.dao;

import com.sysco.sampleService.Stas.exception.DBConnectionException;
import com.sysco.sampleService.Stas.model.HomeApiResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HomeDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public HomeDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private RowMapper<HomeApiResponse> rowMapper = (rs, rowNum) -> {
        return HomeApiResponse.builder()
                .name(rs.getString("name"))
                .L1(rs.getString("l1"))
                .L2(rs.getString("l2"))
                .L3(rs.getString("l3"))
                .build();
    };

    // AnotherClass
    public HomeApiResponse getProductFromDB(String sellerID, String productID) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("SELLER_ID", sellerID)
                .addValue("PRODUCT_ID", productID);

        try {
            return namedParameterJdbcTemplate.queryForObject("""
                    SELECT
                            products.details.value ->> 'name' as name,
                            products.taxonomy.value ->> 'L1' as L1,
                            products.taxonomy.value ->> 'L2' as L2,
                            products.taxonomy.value ->> 'L3' as L3
                        FROM
                            products.details
                        INNER JOIN
                            products.taxonomy
                        ON
                            products.details.seller_id = products.taxonomy.seller_id
                        WHERE
                            products.details.product_id = :PRODUCT_ID AND products.details.seller_id = :SELLER_ID""", mapSqlParameterSource, rowMapper);
            }

        // AnotherClass
        catch (EmptyResultDataAccessException ex){
            return null;
        }
        catch (Exception e){
            throw new DBConnectionException("Shit happened " + e.getMessage());
        }

    }
}