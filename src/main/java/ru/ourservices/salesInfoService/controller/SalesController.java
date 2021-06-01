package ru.ourservices.salesInfoService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ourservices.salesInfoService.model.dto.ApartmentData;
import ru.ourservices.salesInfoService.model.dto.SaleForMonthInfo;
import ru.ourservices.salesInfoService.model.dto.SaleInfo;
import ru.ourservices.salesInfoService.model.dto.SoldApartmentInfo;
import ru.ourservices.salesInfoService.service.SalesService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sales/{code}")
public class SalesController {
    @Autowired
    private SalesService salesService;

    @GetMapping
    public ResponseEntity<List<SaleInfo>> getSoldApartments(@PathVariable(name = "code") String cityCode) {
        List<SaleInfo> sales = salesService.getSoldApartments(cityCode);
        return sales == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping(params = {"startDate","endDate"})
    public ResponseEntity<SaleForMonthInfo> getSoldInfoBy(@PathVariable(name = "code") String cityCode,
                                                          @RequestParam(name = "startDate")
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                          @RequestParam(name = "endDate")
                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        SaleForMonthInfo info = salesService.getSalesInfoBy(cityCode, startDate, endDate);
        return info == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(info, HttpStatus.OK);
    }

    @GetMapping(path = "/unsold")
    public ResponseEntity<List<ApartmentData>> getUnsoldApartments(@PathVariable(name = "code") String cityCode) {
        List<ApartmentData> unsoldApartments = salesService.getUnsoldApartments(cityCode);
        return unsoldApartments == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(unsoldApartments, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> storeApartmentSale(@PathVariable(name = "code") String cityCode,
                                                @Valid @RequestBody SoldApartmentInfo soldApartmentInfo) {
        final boolean modified = salesService.storeApartmentSale(cityCode, soldApartmentInfo);
        return modified
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
