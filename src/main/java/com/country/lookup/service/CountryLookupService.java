package com.country.lookup.service;

import com.country.lookup.rest.model.response.CountryResponse;
import com.country.lookup.service.model.Country;
import com.country.lookup.service.repo.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CountryLookupService {

    private final CountryRepository repository;

    /**
     * Looks up the country for a given phone number. The country is determined based on the phone code of the number.
     *
     * @param phoneNumber The phone number for which to look up the country.
     * @return The country response, which includes a list of countries matching the phone code.
     */
    public CountryResponse lookup(final String phoneNumber) {
        final List<String> prefixes = generatePhonePrefixes(phoneNumber);

        final List<Country> countriesByPhonePrefix = findCountriesByPhonePrefixes(prefixes);

        return createCountryResponse(countriesByPhonePrefix);
    }

    private List<String> generatePhonePrefixes(final String phoneNumber) {
        return IntStream.rangeClosed(1, phoneNumber.length())
                .mapToObj(i -> phoneNumber.substring(0, i))
                .toList();
    }

    private List<Country> findCountriesByPhonePrefixes(final List<String> phonePrefixes) {
        return repository.findByPhoneCodes(phonePrefixes);
    }

    /**
     * Creates a CountryResponse object based on the list of countries matching the longest phone prefix.
     *
     * @param countriesByPhonePrefix The list of Country objects retrieved from the database based on the phone prefixes.
     * @return The CountryResponse object containing a list of country names matching the phone prefixes.
     */
    private CountryResponse createCountryResponse(final List<Country> countriesByPhonePrefix) {
        final List<String> countries = countriesByPhonePrefix.stream()
                .flatMap(country -> country.getPhoneCodes().stream())
                .flatMap(code -> code.getCountries().stream().map(Country::getName))
                .distinct()
                .toList();
        return new CountryResponse(countries);
    }

}
