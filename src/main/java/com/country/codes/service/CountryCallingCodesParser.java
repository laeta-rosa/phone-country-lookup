package com.country.codes.service;

import com.country.codes.service.config.ParserConfig;
import com.country.codes.service.model.CountryCode;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CountryCallingCodesParser {

    private final ParserConfig parserConfig;
    private final CountryCodeRepository countryCodeRepository;

    @PostConstruct
    public void init() {
        updateCountryCodes();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateCountryCodes() {
        try {
            Document doc = Jsoup.connect(parserConfig.getUrl()).get();
            Element table = doc.select("table.wikitable").first();
            Elements rows = table.select("tr");

            // Clear the table before updating
            countryCodeRepository.deleteAll();

            rows.stream()
                    .skip(1) // Skip header row
                    .map(row -> row.select("td"))
                    .filter(cols -> cols.size() > 1)
                    .map(cols -> new CountryCode(cols.get(0).text(), cols.get(1).text()))
                    .forEach(countryCodeRepository::save);

            log.info("Country calling codes updated successfully.");

        } catch (IOException e) {
            log.error("Error updating country calling codes: {}", e.getMessage());
        }
    }

}
