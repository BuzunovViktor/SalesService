package ru.ourservices.salesInfoService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ourservices.salesInfoService.model.entity.Apartment;
import ru.ourservices.salesInfoService.model.dto.City;

import java.util.Arrays;
import java.util.List;

@Service
public class ApartmentService {
    @Autowired
    private RestTemplateBuilder builder;
    @Value("${base.uri}")
    private String baseUri;

    @Cacheable("cities")
    public List<City> getCities() {
        RestTemplate restClient = builder.build();
        ResponseEntity<City[]> response = restClient.getForEntity(baseUri.concat("/cities"), City[].class);
        return Arrays.asList(response.getBody());
    }

    @Cacheable("apartments")
    public List<Apartment> getApartments(String cityCode) {
        RestTemplate restClient = builder.build();
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder.append(baseUri).append("/city").append("/").append(cityCode).append("/apartments");
        ResponseEntity<Apartment[]> response = restClient.getForEntity(uriBuilder.toString(), Apartment[].class);
        return Arrays.asList(response.getBody());
    }
}
