package ru.ourservices.salesInfoService.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Deal")
public class Deal {
    @Id
    private Long id;
    private UUID apartmentId;
    private BigDecimal price;
    private String cityCode;
    private Double area;
    private LocalDate localDate;

    public Deal() {
    }

    public Deal(Long id, UUID apartmentId, BigDecimal price, String cityCode, Double area, LocalDate localDate) {
        this.id = id;
        this.apartmentId = apartmentId;
        this.price = price;
        this.cityCode = cityCode;
        this.area = area;
        this.localDate = localDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
