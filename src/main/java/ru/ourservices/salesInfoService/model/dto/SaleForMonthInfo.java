package ru.ourservices.salesInfoService.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class SaleForMonthInfo implements Serializable {
    private String cityCode;
    private Double sumArea;
    private BigDecimal sumIncome;
    private LocalDate startDate;
    private LocalDate endDate;

    public SaleForMonthInfo() {
    }

    public SaleForMonthInfo(String cityCode, Double sumArea, BigDecimal sumIncome, LocalDate startDate, LocalDate endDate) {
        this.cityCode = cityCode;
        this.sumArea = sumArea;
        this.sumIncome = sumIncome;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Double getSumArea() {
        return sumArea;
    }

    public void setSumArea(Double sumArea) {
        this.sumArea = sumArea;
    }

    public BigDecimal getSumIncome() {
        return sumIncome;
    }

    public void setSumIncome(BigDecimal sumIncome) {
        this.sumIncome = sumIncome;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaleForMonthInfo)) return false;
        SaleForMonthInfo that = (SaleForMonthInfo) o;
        return Objects.equals(cityCode, that.cityCode) && Objects.equals(sumArea, that.sumArea) && Objects.equals(sumIncome, that.sumIncome) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityCode, sumArea, sumIncome, startDate, endDate);
    }

    @Override
    public String toString() {
        return "SaleForMonthInfo{" +
                "cityCode='" + cityCode + '\'' +
                ", sumArea=" + sumArea +
                ", sumIncome=" + sumIncome +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
