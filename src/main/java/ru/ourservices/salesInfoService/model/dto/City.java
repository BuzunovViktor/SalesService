package ru.ourservices.salesInfoService.model.dto;

import java.io.Serializable;
import java.util.Objects;

public class City implements Serializable {
    private String title;
    private String code;

    public City() {
    }

    public City(String title, String code) {
        this.title = title;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "title='" + title + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return title.equals(city.title) && code.equals(city.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, code);
    }
}
