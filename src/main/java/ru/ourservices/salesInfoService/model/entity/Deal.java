package ru.ourservices.salesInfoService.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Deal")
public class Deal {
    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal dealPrice;
    private LocalDate localDate;
    @OneToOne(mappedBy = "deal", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    private Apartment apartment;

    public Deal() {
    }

    public Deal(BigDecimal dealPrice, LocalDate localDate) {
        this.dealPrice = dealPrice;
        this.localDate = localDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(BigDecimal dealPrice) {
        this.dealPrice = dealPrice;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}
