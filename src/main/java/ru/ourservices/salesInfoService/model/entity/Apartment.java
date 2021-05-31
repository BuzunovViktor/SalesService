package ru.ourservices.salesInfoService.model.entity;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Apartment")
public class Apartment implements Serializable {
    @Id
    private Long id;
    private UUID uuid;
    private String cityCode;
    private String number;
    private Double area;
    @MapsId
    @OneToOne
    @JoinColumn(name="id")
    private Deal deal;

    public Apartment() {
    }

    public Apartment(UUID uuid, String cityCode, String number, Double area) {
        this.uuid = uuid;
        this.cityCode = cityCode;
        this.number = number;
        this.area = area;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }
}
