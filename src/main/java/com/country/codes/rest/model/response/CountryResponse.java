package com.country.codes.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CountryResponse {

    private List<String> country;

}
