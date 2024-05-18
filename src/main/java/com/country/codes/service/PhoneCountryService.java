package com.country.codes.service;

import com.country.codes.rest.model.response.CountryResponse;
import org.springframework.stereotype.Service;

@Service
public class PhoneCountryService {

    public CountryResponse identifyCountry(String phoneNumber) {
        return new CountryResponse();
    }

}
