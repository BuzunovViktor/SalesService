package ru.ourservices.salesinfoservice.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ApartmentDTO {
    private Long id;
    private BigDecimal price;
    private Float area;

    public ApartmentDTO(Long id, BigDecimal price, Float area) {
        this.id = id;
        this.price = price;
        this.area = area;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApartmentDTO that = (ApartmentDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(price, that.price) && Objects.equals(area, that.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, area);
    }

    @Override
    public String toString() {
        return "ApartmentDTO{" +
                "id=" + id +
                ", price=" + price +
                ", area=" + area +
                '}';
    }
}
