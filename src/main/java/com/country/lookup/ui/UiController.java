package com.country.lookup.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UiController {

    @GetMapping("/CountryLookup")
    public String countryLookup() {
        return "redirect:/country_lookup.html";
    }

}
