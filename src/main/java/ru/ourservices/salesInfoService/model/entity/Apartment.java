package ru.ourservices.salesInfoService.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Apartment",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"uuid"})})
public class Apartment implements Serializable {
    @Id
    private Long id;
    private UUID uuid;
    @Column(name = "city_code")
    private String cityCode;
    private String number;
    private Float area;
    @MapsId
    @OneToOne
    @JoinColumn(name="id")
    @JsonBackReference
    private Deal deal;

    public Apartment() {
    }

    public Apartment(UUID uuid, String cityCode, String number, Float area) {
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

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apartment)) return false;
        Apartment apartment = (Apartment) o;
        return Objects.equals(getId(), apartment.getId())
                && getUuid().equals(apartment.getUuid())
                && getCityCode().equals(apartment.getCityCode())
                && getNumber().equals(apartment.getNumber())
                && getArea().equals(apartment.getArea())
                && Objects.equals(getDeal(), apartment.getDeal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUuid(), getCityCode(), getNumber(), getArea(), getDeal());
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", cityCode='" + cityCode + '\'' +
                ", number='" + number + '\'' +
                ", area=" + area +
                ", deal=" + deal +
                '}';
    }
}
