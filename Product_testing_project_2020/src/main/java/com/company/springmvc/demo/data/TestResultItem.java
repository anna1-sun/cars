package com.company.springmvc.demo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name="test_result")
public class TestResultItem {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="date")
    private String finishDate;

    @Column(name = "test_value")
    private int testValue;

    @Column(name="category_limit")
    private int categoryLimit;

    @Column(name = "bacteria_name")
    private String bacteriaName;

    @OneToOne (cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @OneToOne (cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "bacteria_id", referencedColumnName = "id")
    private Bacteria bacteria;
}
