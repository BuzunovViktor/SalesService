package ru.ourservices.salesInfoService.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class SoldApartmentInfo {
    private UUID id;
    private LocalDateTime dateTime;
    private BigDecimal price;

    public SoldApartmentInfo() {
    }

    public SoldApartmentInfo(UUID id, LocalDateTime dateTime, BigDecimal price) {
        this.id = id;
        this.dateTime = dateTime;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoldApartmentInfo that = (SoldApartmentInfo) o;
        return Objects.equals(id, that.id)
                && Objects.equals(dateTime, that.dateTime)
                && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, price);
    }

    @Override
    public String toString() {
        return "SoldApartmentInfo{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", price=" + price +
                '}';
    }
}
