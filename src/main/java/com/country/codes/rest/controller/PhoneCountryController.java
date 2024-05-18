package com.country.codes.rest.controller;

import com.country.codes.rest.model.request.PhoneNumberRequest;
import com.country.codes.rest.model.response.CountryResponse;
import com.country.codes.service.PhoneCountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PhoneCountryController {

    private final PhoneCountryService service;

    @PostMapping("/country/{code}")
    public CountryResponse identifyCountry(@RequestBody PhoneNumberRequest phoneNumber) {
        return service.identifyCountry(phoneNumber.getPhoneNumber());
    }
}
