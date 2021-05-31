package ru.ourservices.salesInfoService.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Apartment")
public class Apartment implements Serializable {
    @Id
    private UUID id;
    private String number;
    private Number area;

    public Apartment() {
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

    public Number getArea() {
        return area;
    }

    public void setArea(Number area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return Objects.equals(id, apartment.id) && Objects.equals(number, apartment.number) && Objects.equals(area, apartment.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, area);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", area=" + area +
                '}';
    }
}
