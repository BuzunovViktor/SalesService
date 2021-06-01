package ru.ourservices.salesInfoService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ourservices.salesInfoService.model.aggregation.SumBigDecimalDouble;
import ru.ourservices.salesInfoService.model.entity.Deal;

import java.util.Date;
import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    @Query("SELECT d FROM Deal d " +
            "JOIN Apartment a ON d.apartment.id = a.id " +
            "WHERE LOWER(a.cityCode) = LOWER(:cityCode)")
    List<Deal> getBy(@Param("cityCode") String cityCode);

    @Query("SELECT new ru.ourservices.salesInfoService.model.aggregation.SumBigDecimalDouble" +
            "(SUM(d.income) as income, SUM(a.area) as area) FROM Deal d " +
            "JOIN Apartment a ON d.apartment.id = a.id " +
            "WHERE LOWER(a.cityCode) = LOWER(:cityCode) AND d.date BETWEEN :startDate AND :endDate")
    SumBigDecimalDouble calcIncomeAndArea(@Param("cityCode") String cityCode,
                                          @Param("startDate") Date startDate,
                                          @Param("endDate") Date endDate);
}
