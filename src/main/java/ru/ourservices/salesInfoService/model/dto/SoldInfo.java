package ru.ourservices.salesInfoService.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class SoldInfo implements Serializable {
    private String cityCode;
    private Double area;
    private BigDecimal amount;
    private LocalDate date;

    public SoldInfo(String cityCode, Double area, BigDecimal amount, LocalDate date) {
        this.cityCode = cityCode;
        this.area = area;
        this.amount = amount;
        this.date = date;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SoldInfoDTO{" +
                "cityCode='" + cityCode + '\'' +
                ", area=" + area +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoldInfo that = (SoldInfo) o;
        return cityCode.equals(that.cityCode) && area.equals(that.area) && amount.equals(that.amount) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityCode, area, amount, date);
    }
}
