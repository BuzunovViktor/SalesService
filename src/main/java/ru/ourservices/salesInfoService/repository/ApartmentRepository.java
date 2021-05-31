package ru.ourservices.salesInfoService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ourservices.salesInfoService.model.entity.Apartment;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
