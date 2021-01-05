package com.company.springmvc.demo.dto;

import com.company.springmvc.demo.data.Bacteria;
import com.company.springmvc.demo.data.Limit;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestResultDto {

    private int id;
    private String bacteriaName;
    private int categoryLimit;
    private int value;
    private int bacteriaId;
    private String finishDate;

}

