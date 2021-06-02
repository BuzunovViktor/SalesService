package ru.ourservices.salesInfoService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ourservices.salesInfoService.model.aggregation.SumBigDecimalDouble;
import ru.ourservices.salesInfoService.model.dto.*;
import ru.ourservices.salesInfoService.model.entity.Apartment;
import ru.ourservices.salesInfoService.model.entity.Deal;
import ru.ourservices.salesInfoService.repository.ApartmentRepository;
import ru.ourservices.salesInfoService.repository.DealRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.sql.Date.valueOf;

@Service
public class SalesService {
    private static final Logger logger = LoggerFactory.getLogger("Application");
    @Autowired
    private ApartmentService apartmentService;
    @Autowired
    private DealRepository dealRepository;
    @Autowired
    private ApartmentRepository apartmentRepository;

    public List<SaleInfo> getSoldApartments(String cityCode) {
        City city = findCity(cityCode);
        if (city == null) return null;
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
        return (sum.getArea() == null || sum.getIncome() == null)
                ? null
                : new SaleForMonthInfo(cityCode, sum.getArea(), sum.getIncome(), startDate, endDate);
    }

    public List<ApartmentData> getUnsoldApartments(String cityCode) {
        City city = findCity(cityCode);
        if (city == null) return null;
        List<ApartmentData> apartmentsBase = apartmentService.getApartments(cityCode);
        List<Apartment> soldApartments = apartmentRepository.getBy(cityCode);
        if (soldApartments.size() == 0) return apartmentsBase;
        if (apartmentsBase.size() > 0) {
            Set<UUID> uuids = soldApartments.stream().map(Apartment::getUuid).collect(Collectors.toSet());
            Map<Boolean, List<ApartmentData>> grouped = apartmentsBase.stream()
                    .collect(Collectors.groupingBy(aData->uuids.contains(aData.getId())));
            return grouped.get(false);
        }
        return null;
    }

    public boolean storeApartmentSale(String cityCode, SoldApartmentInfo soldApartment) {
        City city = findCity(cityCode);
        if (city == null) return false;
        Apartment apartment = apartmentRepository.getByUUID(soldApartment.getId());
        if (apartment != null) return false;
        List<ApartmentData> apartmentsBase = apartmentService.getApartments(city.getCode());
        Optional<ApartmentData> optionalApartmentData = apartmentsBase.stream()
                .filter(a->a.getId().equals(soldApartment.getId())).findFirst();
        if (optionalApartmentData.isPresent()) {
            ApartmentData data = optionalApartmentData.get();
            Apartment dealApartment = new Apartment(data.getId(), city.getCode(), data.getNumber(), data.getArea());
            Deal deal = new Deal(soldApartment.getPrice(), valueOf(soldApartment.getDateTime().toLocalDate()));
            deal.setApartment(dealApartment);
            dealApartment.setDeal(deal);
            dealRepository.saveAndFlush(deal);
            logger.info("Deal created and saved. " + deal);
            return true;
        }
        return false;
    }

    private City findCity(String cityCode) {
        List<City> cities = apartmentService.getCities();
        Optional<City> cityOptional = cities.stream().filter(city -> city.getCode().equals(cityCode)).findFirst();
        return cityOptional.orElse(null);
    }
}
