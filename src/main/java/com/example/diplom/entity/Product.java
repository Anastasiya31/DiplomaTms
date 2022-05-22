package com.example.diplom.entity;

import javax.persistence.*;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
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
        return "Product{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", grade='" + grade + '\'' +
                ", metalCategory=" + metalCategory +
                '}';
    }
}
