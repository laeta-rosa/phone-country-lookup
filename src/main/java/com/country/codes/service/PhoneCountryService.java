package com.country.codes.service;

import com.country.codes.rest.model.response.CountryResponse;
import com.country.codes.service.model.CountryCode;
import com.country.codes.service.repo.CountryCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneCountryService {

    private final CountryCodeRepository repository;

    public List<CountryCode> getAllCountries() {
        return repository.findAll();
    }

    public CountryResponse identifyCountry(String phoneNumber) {
        // Generate all possible prefixes
        List<String> prefixes = IntStream.rangeClosed(1, phoneNumber.length())
                .mapToObj(i -> phoneNumber.substring(0, i))
                .collect(Collectors.toList());

        // Query database for all prefixes
        List<CountryCode> countryCodes = repository.findByCountryCodes(prefixes);

        // Find the longest matching prefix
        return new CountryResponse(countryCodes.stream()
                .filter(code -> phoneNumber.startsWith(code.getCountryCode()))
                .sorted((code1, code2) -> Integer.compare(code2.getCountryCode().length(), code1.getCountryCode().length()))
                .map(CountryCode::getCountryName)
                .distinct()
                .collect(Collectors.toList()));
    }

}
