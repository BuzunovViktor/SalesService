package ru.ourservices.salesInfoService.model.aggregation;

import java.math.BigDecimal;
import java.util.Objects;

public class SumBigDecimalDouble {
    private BigDecimal income;
    private Double area;

    public SumBigDecimalDouble() {
    }

    public SumBigDecimalDouble(BigDecimal income, Double area) {
        this.income = income;
        this.area = area;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SumBigDecimalDouble)) return false;
        SumBigDecimalDouble that = (SumBigDecimalDouble) o;
        return Objects.equals(getIncome(), that.getIncome()) && Objects.equals(getArea(), that.getArea());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIncome(), getArea());
    }

    @Override
    public String toString() {
        return "DealSumIncomeSumAreaAgregation{" +
                "income=" + income +
                ", area=" + area +
                '}';
    }
}
