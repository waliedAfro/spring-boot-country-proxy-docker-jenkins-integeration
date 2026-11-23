package com.afro.country.proxy.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.afro.country.proxy.dto.CountryResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final RestClient restClient;

     public CountryResponse getCountry(String name) {

   ExternalCountry[] countries = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("name/{name}")
                        .queryParam("fields", "name,capital,population,region,currencies,flags")
                        .build(name))
                .retrieve()
                .body(ExternalCountry[].class);

        if (countries == null || countries.length == 0) {
            throw new RuntimeException("Country not found: " + name);
        }

        ExternalCountry c = countries[0];

        List<String> currencyCodes = c.currencies() == null
                ? List.of()
                : c.currencies().stream().map(Currency::code).toList();

        return new CountryResponse(
                c.name(),                                   // now a String
                c.capital() == null ? "N/A" : c.capital(),  // now a String
                c.population(),
                c.region(),
                currencyCodes,
                c.flags() == null ? null : c.flags().png()
        );
    }

    // ---- Matches the actual JSON returned by the API ----

    private record ExternalCountry(
            String name,
            String capital,
            long population,
            String region,
            List<Currency> currencies,
            Flags flags
    ) {}

    private record Currency(String code, String name, String symbol) {}
    private record Flags(String png, String svg) {}
}
