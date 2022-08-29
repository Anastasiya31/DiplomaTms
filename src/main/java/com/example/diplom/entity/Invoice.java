package com.example.diplom.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "invoice", schema = "diploma")
public class Invoice implements Serializable {

    @Serial
    private static final long serialVersionUID = -3681329161655222674L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date")
    private Date data;
    @Column(name = "payment_details")
    private String paymentDetails;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @Enumerated
    private InvoiceType invoiceType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(id, invoice.id) && Objects.equals(data, invoice.data) && Objects.equals(paymentDetails, invoice.paymentDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, paymentDetails);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", data=" + data +
                ", paymentDetails='" + paymentDetails + '\'' +
                ", order=" + order +
                ", company=" + company +
                ", invoiceType=" + invoiceType +
                '}';
    }
}
