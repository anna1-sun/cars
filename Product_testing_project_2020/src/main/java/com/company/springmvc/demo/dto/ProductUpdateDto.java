package com.company.springmvc.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductUpdateDto {
    private String code;
    private String name;
    private int shelfLife;
    private int categoryId;
}

