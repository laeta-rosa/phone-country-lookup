package com.country.lookup.rest.controller;

import com.country.lookup.rest.model.request.PhoneNumberRequest;
import com.country.lookup.rest.model.response.CountryResponse;
import com.country.lookup.service.CountryLookupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PhoneCountryController {

    private final CountryLookupService service;

    /**
     * Looks up the country for a given phone number. The country is determined based on the phone code of the number.
     *
     * @param phoneNumber The phone number for which to look up the country.
     * @return The country response, which includes a list of countries matching the phone code.
     */
    @PostMapping("/lookup-country")
    public CountryResponse lookupCountry(@RequestBody PhoneNumberRequest phoneNumber) {
        return service.lookup(phoneNumber.getPhoneNumber());
    }

}
