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
@Table(name = "company", schema = "diploma")
public class Company implements Serializable {

    @Serial
    private static final long serialVersionUID = 7101339175003201522L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "company_title")
    private String companyTitle;
    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @Column(name = "telephone")
    private String tel;
    @Column(name = "email")
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) && Objects.equals(companyTitle, company.companyTitle) && Objects.equals(country, company.country) && Objects.equals(tel, company.tel) && Objects.equals(email, company.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyTitle, country, tel, email);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", companyTitle='" + companyTitle + '\'' +
                ", country='" + country + '\'' +
                ", telephone='" + tel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
