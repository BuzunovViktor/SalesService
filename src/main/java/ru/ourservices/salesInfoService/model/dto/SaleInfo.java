package ru.ourservices.salesInfoService.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class SaleInfo {
    private UUID apartmentId;
    private BigDecimal price;
    private LocalDate date;

    public SaleInfo() {
    }

    public SaleInfo(UUID apartmentId, BigDecimal price, LocalDate date) {
        this.apartmentId = apartmentId;
        this.price = price;
        this.date = date;
    }

    public UUID getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(UUID apartmentId) {
        this.apartmentId = apartmentId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaleInfo)) return false;
        SaleInfo saleInfo = (SaleInfo) o;
        return getApartmentId().equals(saleInfo.getApartmentId()) && getPrice().equals(saleInfo.getPrice()) && getDate().equals(saleInfo.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getApartmentId(), getPrice(), getDate());
    }

    @Override
    public String toString() {
        return "SaleInfo{" +
                "apartmentId=" + apartmentId +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
