package com.example.diplom.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product", schema = "diploma")
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = -445834315838125836L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "grade")
    private String grade;

    @Column(name = "cost_price")
    private BigDecimal costPrice;

    @Column(name = "company")
    private String company;
    @Column(name = "markup")
    private double markup;
    // @Formula("cost_price + (cost_price * markup / 100)")
    @Column(name = "final_price")
    private BigDecimal finalPrice;

    public BigDecimal getFinalPrice() {

        BigDecimal f = costPrice.multiply(new BigDecimal(markup/100));
        return costPrice.add(f);
    }

    @ManyToOne
    @JoinColumn(name = "metal_category_id")
    private MetalCategory metalCategory;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(type, product.type) && Objects.equals(grade, product.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, grade);
    }

    @Override
    public String toString() {
        return grade;
    }
}
