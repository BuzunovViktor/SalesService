package ru.ourservices.salesInfoService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ourservices.salesInfoService.model.dto.SaleInfo;
import ru.ourservices.salesInfoService.model.entity.Apartment;
import ru.ourservices.salesInfoService.model.dto.SoldInfo;
import ru.ourservices.salesInfoService.model.entity.Deal;
import ru.ourservices.salesInfoService.service.SalesService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sales/{code}")
public class SalesController {
    @Autowired
    private SalesService salesService;

    @GetMapping
    public ResponseEntity<List<Deal>> getSoldApartments(@PathVariable(name = "code") String cityCode) {
        List<Deal> apartments = salesService.getSoldApartments(cityCode);
        return apartments == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(apartments, HttpStatus.OK);
    }

    @GetMapping(params = {"date"})
    public ResponseEntity<SoldInfo> getSoldInfoBy(@PathVariable(name = "code") String cityCode,
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        SoldInfo info = salesService.getSoldApartmentsInfoBy(cityCode, date);
        return info == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(info, HttpStatus.OK);
    }

    @GetMapping(path = "/unsold")
    public ResponseEntity<List<Apartment>> getUnsoldApartments(@PathVariable(name = "code") String cityCode) {
        List<Apartment> unsoldApartments = salesService.getUnsoldApartments(cityCode);
        return new ResponseEntity<>(unsoldApartments, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> storeApartmentSale(@PathVariable(name = "code") String cityCode,
                                                @RequestBody SaleInfo saleInfo) {
        final boolean modified = salesService.storeApartmentSale(cityCode, saleInfo);
        return modified
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
