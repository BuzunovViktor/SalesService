package ru.ourservices.salesinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ourservices.salesinfoservice.model.ApartmentDTO;
import ru.ourservices.salesinfoservice.model.BaseResponse;
import ru.ourservices.salesinfoservice.service.SalesService;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {
    @Autowired
    private SalesService salesService;

    @GetMapping
    public ResponseEntity<List<ApartmentDTO>> getSoldApartments() {
        List<ApartmentDTO> apartments =  salesService.getSoldApartments();
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping("/sold")
    public BaseResponse pay(@RequestParam(value = "key") String key, @RequestBody PaymentRequest request) {
        return new ResponseEntity<List<ApartmentDTO>>(, HttpStatus.OK);;
    }

}
