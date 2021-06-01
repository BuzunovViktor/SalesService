package ru.ourservices.salesInfoService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ourservices.salesInfoService.model.entity.Apartment;
import ru.ourservices.salesInfoService.model.entity.Deal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class SalesinfoserviceApplicationTests {

	@Test
	void contextLoads() {
		Apartment apartment = new Apartment(UUID.randomUUID(), "ekb", "123123",40F);
		Deal deal = new Deal(new BigDecimal(100), java.sql.Date.valueOf(LocalDate.now()));
		deal.setApartment(apartment);
		apartment.setDeal(deal);
	}


	//        Session session = sessionFactory.openSession();
//        System.out.println("two");
//        Query<SumBigDecimalDouble> query = session.createQuery(
//                "SELECT new ru.ourservices.salesInfoService.model.aggregation.SumBigDecimalDouble" +
//                        "(SUM(d.dealPrice) as income, SUM(a.area) as area)" +
//                        "FROM ru.ourservices.salesInfoService.model.entity.Deal d INNER JOIN " +
//                        "ru.ourservices.salesInfoService.model.entity.Apartment a ON d.apartment.id = a.id " +
//                        "WHERE d.apartment.cityCode = :cityCode AND d.localDate = :date", SumBigDecimalDouble.class);
//        query.setParameter("cityCode", "ekb");
//        query.setParameter("date", deal.getLocalDate());
//        System.out.println("3");
//        SumBigDecimalDouble val = query.getSingleResult();
	//session.close();
}
