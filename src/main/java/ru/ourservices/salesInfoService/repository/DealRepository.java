package ru.ourservices.salesInfoService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ourservices.salesInfoService.model.aggregation.SumBigDecimalDouble;
import ru.ourservices.salesInfoService.model.entity.Deal;

import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {
    @Query("SELECT d FROM Deal d WHERE LOWER(d.cityCode) = LOWER(:cityCode)")
    List<Deal> findByCode(@Param("cityCode") String cityCode);

    @Query("SELECT new ru.ourservices.salesInfoService.model.aggregation.SumBigDecimalDouble" +
            "(SUM(d.price) as income, SUM(d.area) as area)" +
            "FROM Deal d WHERE LOWER(d.cityCode) = LOWER(:cityCode)")
    SumBigDecimalDouble calculateIncomeAndArea(@Param("cityCode") String cityCode);
}
