package ru.ourservices.salesInfoService.model.dto;

import java.util.Objects;
import java.util.UUID;

public class ApartmentData {
    private UUID id;
    private String number;
    private Float area;

    public ApartmentData() {
    }

    public ApartmentData(UUID uuid, String number, Float area) {
        this.id = uuid;
        this.number = number;
        this.area = area;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApartmentData)) return false;
        ApartmentData that = (ApartmentData) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getNumber(), that.getNumber())
                && Objects.equals(getArea(), that.getArea());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber(), getArea());
    }

    @Override
    public String toString() {
        return "ApartmentData{" +
                "uuid=" + id +
                ", number='" + number + '\'' +
                ", area=" + area +
                '}';
    }
}
