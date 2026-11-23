package com.afro.country.proxy.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afro.country.proxy.dto.CountryResponse;
import com.afro.country.proxy.service.CountryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/countries")
@Slf4j
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{name}")
    public CountryResponse getCountry(@PathVariable String name) {
        log.info("Country {}", name);
        return countryService.getCountry(name);
    }
}
