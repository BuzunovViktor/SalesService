package ru.ourservices.salesInfoService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.ourservices.salesInfoService.model.dto.ApartmentData;
import ru.ourservices.salesInfoService.model.dto.City;

import java.util.Arrays;
import java.util.List;

@Service
public class ApartmentService {
    @Autowired
    private RestTemplateBuilder builder;
    @Value("${external.base.url}")
    private String baseUrl;

    @Cacheable("cities")
    public List<City> getCities() {
        RestTemplate restClient = builder.build();
        ResponseEntity<City[]> resp = restClient.getForEntity(baseUrl.concat("/cities"), City[].class);
        checkStatus(resp.getStatusCode());
        return Arrays.asList(resp.getBody());
    }

    @Cacheable("apartments")
    public List<ApartmentData> getApartments(String cityCode) {
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder.append(baseUrl).append("/city").append("/").append(cityCode).append("/apartments");
        RestTemplate restClient = builder.build();
        ResponseEntity<ApartmentData[]> resp = restClient.getForEntity(uriBuilder.toString(), ApartmentData[].class);
        checkStatus(resp.getStatusCode());
        return Arrays.asList(resp.getBody());
    }

    private boolean checkStatus(HttpStatus status) {
        if (status != HttpStatus.OK) {
            throw new HttpClientErrorException(status);
        }
        return true;
    }
}
