package ru.ourservices.salesInfoService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ourservices.salesInfoService.model.dto.*;
import ru.ourservices.salesInfoService.model.entity.Apartment;
import ru.ourservices.salesInfoService.repository.DealRepository;
import ru.ourservices.salesInfoService.model.aggregation.SumBigDecimalDouble;
import ru.ourservices.salesInfoService.model.entity.Deal;
import ru.ourservices.salesInfoService.repository.ApartmentRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SalesService {
    @Autowired
    private ApartmentService apartmentService;
    @Autowired
    private DealRepository dealRepository;
    @Autowired
    private ApartmentRepository apartmentRepository;

    public List<SaleInfo> getSoldApartments(String cityCode) {
        City city = findCity(cityCode);
        if (city == null) return null;
        Apartment apartment = new Apartment(UUID.randomUUID(), "ekb", "123123",40F);
        Deal deal = new Deal(new BigDecimal(100), java.sql.Date.valueOf(LocalDate.now()));
        deal.setApartment(apartment);
        apartment.setDeal(deal);
        dealRepository.saveAndFlush(deal);
        List<Deal> deals = dealRepository.getBy(city.getCode());
        if (deals != null && deals.size() > 0) {
            List<SaleInfo> sales = new ArrayList<>();
            deals.forEach(d->{
                SaleInfo saleInfo = new SaleInfo(d.getApartment().getUuid(),
                        d.getApartment().getArea(), d.getDate(), d.getIncome());
                sales.add(saleInfo);
            });
            return sales;
        }
        return null;
    }

    public SaleForMonthInfo getSalesInfoBy(String cityCode, LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) throw new IllegalArgumentException("End date is less than start date.");
        City city = findCity(cityCode);
        if (city == null) return null;
        SumBigDecimalDouble sum = dealRepository.calcIncomeAndArea(cityCode,
                java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate));
        return new SaleForMonthInfo(cityCode, sum.getArea(), sum.getIncome(), startDate, endDate);
    }

    public List<ApartmentData> getUnsoldApartments(String cityCode) {
        List<ApartmentData> apartmentsFrom = apartmentService.getApartments(cityCode);
        List<Apartment> soldApartments = apartmentRepository.getBy(cityCode);
        if (soldApartments.size() == 0) return apartmentsFrom;
        if (apartmentsFrom.size() > 0) {
            Set<UUID> uuids = soldApartments.stream().map(Apartment::getUuid).collect(Collectors.toSet());
            apartmentsFrom.removeIf(a->uuids.contains(a.getId()));
            return apartmentsFrom;
        }
        return null;
    }

    public boolean storeApartmentSale(String cityCode, SoldApartmentInfo soldApartmentInfo) {
        Apartment apartment= apartmentRepository.getByUUID(soldApartmentInfo.getId());
        return apartment == null;
    }

    private City findCity(String cityCode) {
        List<City> cities = apartmentService.getCities();
        Optional<City> cityOptional = cities.stream().filter(city -> city.getCode().equals(cityCode)).findAny();
        return cityOptional.orElse(null);
    }
}
