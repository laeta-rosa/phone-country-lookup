package com.country.codes.rest.controller;

import com.country.codes.rest.model.request.PhoneNumberRequest;
import com.country.codes.rest.model.response.CountryResponse;
import com.country.codes.service.PhoneCountryService;
import com.country.codes.service.model.CountryCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PhoneCountryController {

    private final PhoneCountryService service;

    @PostMapping("/country")
    public CountryResponse identifyCountry(@RequestBody PhoneNumberRequest phoneNumber) {
        return service.identifyCountry(phoneNumber.getPhoneNumber());
    }

    @GetMapping("/all")
    public List<CountryCode> allCountries() {
        return service.getAllCountries();
    }
}
