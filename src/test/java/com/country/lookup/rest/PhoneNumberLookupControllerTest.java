package com.country.lookup.rest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;
import java.util.stream.Stream;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PhoneNumberLookupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("providePhoneNumbersAndCountries")
    public void lookupCountryByValidPhoneNumber(String phoneNumber, Set<String> expectedCountries) throws Exception {
        String jsonRequest = "{ \"phoneNumber\": \"" + phoneNumber + "\" }";

        this.mockMvc.perform(post("/lookup-country")
                        .contentType(APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country", containsInAnyOrder((expectedCountries.toArray(new String[0])))));
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void lookupCountryByEmptyOrNullPhoneNumber(String phoneNumber) throws Exception {
        String jsonRequest = "{ \"phoneNumber\": \"" + phoneNumber + "\" }";

        this.mockMvc.perform(post("/lookup-country")
                        .contentType(APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country", is(empty())));
    }

    private static Stream<Arguments> providePhoneNumbersAndCountries() {
        return Stream.of(
                Arguments.of("5993000000", Set.of("Sint Eustatius", "Caribbean Netherlands")),
                Arguments.of("5997225000", Set.of("Bonaire", "Caribbean Netherlands")),
                Arguments.of("12423222931", Set.of("Bahamas")),
                Arguments.of("11165384765", Set.of("United States", "Canada")),
                Arguments.of("71423423412", Set.of("Russia")),
                Arguments.of("77112227231", Set.of("Kazakhstan")),
                Arguments.of("390669883135", Set.of("Vatican City State (Holy See)")),
                Arguments.of("77272672886", Set.of("Kazakhstan")),
                Arguments.of("78402267069", Set.of("Abkhazia")),
                Arguments.of("37125685655", Set.of("Latvia")),
                Arguments.of("99365533100", Set.of("Turkmenistan")),
                Arguments.of("18299548361", Set.of("Dominican Republic")),
                Arguments.of("18499566361", Set.of("Dominican Republic")),
                Arguments.of("18099548721", Set.of("Dominican Republic")),
                Arguments.of("4962215150", Set.of("Germany")),
                Arguments.of("35425686644", Set.of("Iceland")),
                Arguments.of("166438777701", Set.of("Montserrat")),
                Arguments.of("903923000000", Set.of("Northern Cyprus")),
                Arguments.of("17873000000", Set.of("Puerto Rico")),
                Arguments.of("1939000000", Set.of("Puerto Rico")),
                Arguments.of("41445622231", Set.of("Switzerland")),
                Arguments.of("902125122350", Set.of("Turkey")),
                Arguments.of("442033981910", Set.of("United Kingdom")),
                Arguments.of("6620802111", Set.of("Thailand")),
                Arguments.of("380362649141", Set.of("Ukraine")),
                Arguments.of("48223288888", Set.of("Poland")),
                Arguments.of("9714", Set.of("United Arab Emirates"))
        );
    }

}
