package ru.ourservices.salesInfoService.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Deal")
public class Deal {
    @Id
    @GeneratedValue
    private Long id;
    private BigDecimal income;
    @Basic
    @Temporal(TemporalType.DATE)
    private Date date;
    @OneToOne(mappedBy = "deal", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    @JsonManagedReference
    private Apartment apartment;

    public Deal() {
    }

    public Deal(BigDecimal income, Date date) {
        this.income = income;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deal)) return false;
        Deal deal = (Deal) o;
        return Objects.equals(getId(), deal.getId())
                && getIncome().equals(deal.getIncome())
                && getDate().equals(deal.getDate())
                && getApartment().equals(deal.getApartment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIncome(), getDate(), getApartment());
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", income=" + income +
                ", date=" + date +
                ", apartment=" + apartment +
                '}';
    }
}
