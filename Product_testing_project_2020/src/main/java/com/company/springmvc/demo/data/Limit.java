package com.company.springmvc.demo.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BacLimit")
@IdClass(CategoryBacteriaId.class)
public class Limit {

    @Id
    @ManyToOne(targetEntity = Category.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Id
    @ManyToOne(targetEntity = Bacteria.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "bacteria_id", referencedColumnName = "id")
    private Bacteria bacteria;

    @Column(name = "baclimit_value")
    private Integer limit;

}
