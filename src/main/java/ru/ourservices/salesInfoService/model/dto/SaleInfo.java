package ru.ourservices.salesInfoService.model.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class SaleInfo {
    private UUID apartmentId;
    private Float apartmentArea;
    private Date dealDate;
    private BigDecimal dealPrice;

    public SaleInfo() {
    }

    public SaleInfo(UUID apartmentId, Float apartmentArea, Date dealDate, BigDecimal dealPrice) {
        this.apartmentId = apartmentId;
        this.apartmentArea = apartmentArea;
        this.dealDate = dealDate;
        this.dealPrice = dealPrice;
    }

    public UUID getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(UUID apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Float getApartmentArea() {
        return apartmentArea;
    }

    public void setApartmentArea(Float apartmentArea) {
        this.apartmentArea = apartmentArea;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public BigDecimal getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(BigDecimal dealPrice) {
        this.dealPrice = dealPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaleInfo)) return false;
        SaleInfo saleInfo = (SaleInfo) o;
        return Objects.equals(getApartmentId(), saleInfo.getApartmentId())
                && Objects.equals(getApartmentArea(), saleInfo.getApartmentArea())
                && Objects.equals(getDealDate(), saleInfo.getDealDate())
                && Objects.equals(getDealPrice(), saleInfo.getDealPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getApartmentId(), getApartmentArea(), getDealDate(), getDealPrice());
    }

    @Override
    public String toString() {
        return "SaleInfo{" +
                "apartmentId=" + apartmentId +
                ", apartmentArea=" + apartmentArea +
                ", dealDate=" + dealDate +
                ", dealPrice=" + dealPrice +
                '}';
    }
}
