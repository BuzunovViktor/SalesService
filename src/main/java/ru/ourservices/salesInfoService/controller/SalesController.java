package ru.ourservices.salesInfoService.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Получить список проданных квартир в городе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список квартир",
                            content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SaleInfo.class)) }),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                            content = @Content),
            @ApiResponse(responseCode = "404", description = "Не найдено",
                            content = @Content) })
    @GetMapping
    public ResponseEntity<List<SaleInfo>> getSoldApartments(@PathVariable(name = "code") String cityCode) {
        List<SaleInfo> sales = salesService.getSoldApartments(cityCode);
        return sales == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @Operation(summary = "Получить информацию о проданных квартирах в промежутке дат." +
            "пример для одного месяца startDate=2021-06-01  endDate=2021-07-01" +
            "startDate - включительно, endDate=исключительно")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о продажах",
                            content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SaleForMonthInfo.class)) }),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                            content = @Content),
            @ApiResponse(responseCode = "404", description = "Не найдено",
                            content = @Content) })
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

    @Operation(summary = "Получить информацию о не проданных квартирах в городе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список квартир",
                            content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApartmentData.class)) }),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                            content = @Content),
            @ApiResponse(responseCode = "404", description = "Не найдено",
                            content = @Content) })
    @GetMapping(path = "/unsold")
    public ResponseEntity<List<ApartmentData>> getUnsoldApartments(@PathVariable(name = "code") String cityCode) {
        List<ApartmentData> unsoldApartments = salesService.getUnsoldApartments(cityCode);
        return unsoldApartments == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(unsoldApartments, HttpStatus.OK);
    }

    @Operation(summary = "Сохранить информацию о продаже квартиры")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Создано"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос"),
            @ApiResponse(responseCode = "304", description = "Не изменено")})
    @PutMapping
    public ResponseEntity<?> storeApartmentSale(@PathVariable(name = "code") String cityCode,
                                                @Valid @RequestBody SoldApartmentInfo soldApartmentInfo) {
        final boolean modified = salesService.storeApartmentSale(cityCode, soldApartmentInfo);
        return modified
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
