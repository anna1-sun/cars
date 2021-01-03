package com.company.springmvc.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class ProductUpdateDto {
    private String code;
    private String name;
    private int shelfLife;
    private int categoryId;
    private int monthId;
}

