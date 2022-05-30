package com.example.diplom.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "metal_category", schema = "diploma")
public class MetalCategory implements Serializable {

    @Serial
    private static final long serialVersionUID = -1488093480309741102L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category")
    private String category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetalCategory that = (MetalCategory) o;
        return Objects.equals(id, that.id) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category);
    }

    @Override
    public String toString() {
        return "MetalCategory{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }
}
