package ru.ourservices.salesInfoService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.ourservices.salesInfoService.model.entity.Apartment;

import java.util.List;
import java.util.UUID;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    @Query("SELECT a FROM Apartment a WHERE a.cityCode = :cityCode")
    List<Apartment> getBy(@Param("cityCode") String cityCode);

    @Query("SELECT a FROM Apartment a WHERE a.uuid = :uuid")
    Apartment getByUUID(@Param("uuid") UUID UUID);
}
