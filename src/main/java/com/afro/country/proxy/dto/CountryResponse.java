package com.afro.country.proxy.dto;

import java.util.List;




public record CountryResponse(
    String name,
        String capital,
        long population,
        String region,
        List<String> currencies,
        String flagUrl 
) {
} 
        
