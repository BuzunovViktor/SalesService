package ru.ourservices.salesInfoService.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ourservices.salesInfoService.model.aggregation.SumBigDecimalDouble;
import ru.ourservices.salesInfoService.model.dto.SaleInfo;
import ru.ourservices.salesInfoService.model.entity.Apartment;
import ru.ourservices.salesInfoService.model.dto.City;
import ru.ourservices.salesInfoService.model.dto.SoldInfo;
import ru.ourservices.salesInfoService.model.entity.Deal;
import ru.ourservices.salesInfoService.repository.ApartmentRepository;
import ru.ourservices.salesInfoService.repository.DealRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
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
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private SessionFactory sessionFactory;

    public List<Deal> getSoldApartments(String cityCode) {
        City city = findCity(cityCode);
        if (city == null) return null;
        return dealRepository.findByCode(city.getCode());
    }

    public SoldInfo getSoldApartmentsInfoBy(String cityCode, LocalDate date) {


        List<City> cities = apartmentService.getCities();
        Apartment apartment = new Apartment(UUID.randomUUID(), "ekb", "123123",40.);
        Deal deal = new Deal(new BigDecimal(100),LocalDate.now());
        deal.setApartment(apartment);
        apartment.setDeal(deal);
        System.out.println(-2);
        dealRepository.save(deal);
        System.out.println("one");
        Session session = sessionFactory.openSession();
        System.out.println("two");
        Query<SumBigDecimalDouble> query = session.createQuery(
                "SELECT new ru.ourservices.salesInfoService.model.aggregation.SumBigDecimalDouble" +
                        "(SUM(d.dealPrice) as income, SUM(a.area) as area)" +
                        "FROM ru.ourservices.salesInfoService.model.entity.Deal d INNER JOIN " +
                        "ru.ourservices.salesInfoService.model.entity.Apartment a ON d.apartment.id = a.id " +
                        "WHERE d.apartment.cityCode = :cityCode AND d.localDate = :date", SumBigDecimalDouble.class);
        query.setParameter("cityCode", "ekb");
        query.setParameter("date", deal.getLocalDate());
        System.out.println("3");
        SumBigDecimalDouble val = query.getSingleResult();
        System.out.println(val);
        System.out.println("4");
//        SumBigDecimalDouble incoming = dealRepository.calculateIncomeAndArea(cityCode);
//        System.out.println(incoming);
        //dealRepository.findAll().forEach(System.out::println);
        session.close();
        return new SoldInfo(cityCode, val.getArea(), val.getIncome(),deal.getLocalDate());
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
