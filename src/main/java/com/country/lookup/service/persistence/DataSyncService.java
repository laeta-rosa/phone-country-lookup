package com.country.lookup.service.persistence;

import com.country.lookup.service.model.Country;
import com.country.lookup.service.model.PhoneCode;
import com.country.lookup.service.parser.PhoneCodesParser;
import com.country.lookup.service.repo.PhoneCodeRepository;
import com.country.lookup.service.repo.CountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataSyncService {

    private final static String TABLE_ELEMENT = "table.wikitable";
    private final static String ROWS_ELEMENT = "tr";
    private final static String COLS_ELEMENT = "td";

    private final static String SYNC_SUCCESS = "Country calling codes updated successfully.";
    private final static String SYNC_FAIL = "Error updating country calling codes: {}.";

    private final PhoneCodesParser phoneCodesParser;
    private final CountryRepository countryRepository;
    private final PhoneCodeRepository phoneCodeRepository;

    /**
     * Synchronizes country calling codes from a URL stored in application.yml.
     * Deletes all existing country and phone code records in the database and inserts freshly synced data.
     *
     * @param url the URL to fetch the country calling codes table from
     */
    @Transactional
    public void sync(final String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Element table = doc.select(TABLE_ELEMENT).first();
            Elements rows = table.select(ROWS_ELEMENT);

            countryRepository.deleteAll();
            phoneCodeRepository.deleteAll();

            for (Element row : rows) {
                Elements cols = row.select(COLS_ELEMENT);
                if (cols.size() > 1) {
                    String countryName = cols.get(0).text();
                    String codesString = cols.get(1).text();
                    Set<String> codes = phoneCodesParser.parsePhoneCodes(codesString);

                    Set<PhoneCode> phoneCodes = new HashSet<>();
                    for (String code : codes) {
                        PhoneCode phoneCode = phoneCodeRepository.findByCode(code);
                        if (phoneCode == null) {
                            phoneCode = new PhoneCode();
                            phoneCode.setCode(code);
                            phoneCodeRepository.save(phoneCode);
                        }
                        phoneCodes.add(phoneCode);
                    }

                    Country country = new Country();
                    country.setName(countryName);
                    country.setPhoneCodes(phoneCodes);

                    countryRepository.save(country);
                }
            }
            log.info(SYNC_SUCCESS);
        } catch (IOException e) {
            log.error(SYNC_FAIL, e.getMessage(), e);
        }
    }

}
