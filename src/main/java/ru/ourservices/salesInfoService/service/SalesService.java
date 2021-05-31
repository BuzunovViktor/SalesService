package ru.ourservices.salesInfoService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ourservices.salesInfoService.model.aggregation.SumBigDecimalDouble;
import ru.ourservices.salesInfoService.model.dto.SaleInfo;
import ru.ourservices.salesInfoService.model.entity.Apartment;
import ru.ourservices.salesInfoService.model.dto.City;
import ru.ourservices.salesInfoService.model.dto.SoldInfo;
import ru.ourservices.salesInfoService.model.entity.Deal;
import ru.ourservices.salesInfoService.repository.DealRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SalesService {
    @Autowired
    private ApartmentService apartmentService;
    @Autowired
    private DealRepository dealRepository;

    public List<Deal> getSoldApartments(String cityCode) {
        City city = findCity(cityCode);
        if (city == null) return null;
        return dealRepository.findByCode(city.getCode());
    }

    public SoldInfo getSoldApartmentsInfoBy(String cityCode, LocalDate date) {
        List<City> cities = apartmentService.getCities();
        Deal deal = new Deal(123L, UUID.randomUUID(),new BigDecimal(100),"ekb",250.,LocalDate.now());
        dealRepository.save(deal);
        Deal deal2 = new Deal(124L, UUID.randomUUID(),new BigDecimal(500),"ekb",250.,LocalDate.now());
        dealRepository.save(deal2);
        Deal deal3 = new Deal(125L, UUID.randomUUID(),new BigDecimal(500),"msk",250.,LocalDate.now());
        dealRepository.save(deal3);
        SumBigDecimalDouble incoming = dealRepository.calculateIncomeAndArea(cityCode);
        System.out.println(incoming);
        dealRepository.findAll().forEach(System.out::println);
        return new SoldInfo(null, incoming.getArea(), incoming.getIncome(),null);
    }

    public List<Apartment> getUnsoldApartments(String cityCode) {
        List<Apartment> apartmentsFrom = apartmentService.getApartments(cityCode);
//        List<Apartment> unsoldApartments = apartmentsFrom.stream()
//                .filter(Predicate.not(a->a.getId()::equals))
//                .collect(Collectors.toList());
        return new ArrayList<>();
    }

    public boolean storeApartmentSale(String cityCode, SaleInfo saleInfo) {
        return false;
    }

    private City findCity(String cityCode) {
        List<City> cities = apartmentService.getCities();
        Optional<City> cityOptional = cities.stream().filter(city -> city.getCode().equals(cityCode)).findAny();
        return cityOptional.orElse(null);
    }
}
