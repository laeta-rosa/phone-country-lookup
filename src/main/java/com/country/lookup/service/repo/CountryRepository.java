package com.country.lookup.service.repo;

import com.country.lookup.service.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    /**
     * Finds a list of countries based on the given phone code prefixes.
     * The countries are selected based on the longest matching prefix.
     *
     * @param prefixes The list of phone code prefixes to search for countries.
     * @return A list of countries that have phone codes matching the given prefixes.
     */
    @Query("""
            SELECT c FROM Country c
            JOIN c.phoneCodes cc
            WHERE cc.code IN :prefixes AND LENGTH(cc.code) = (
                SELECT MAX(LENGTH(cc1.code)) FROM PhoneCode cc1 
                WHERE cc1.code IN :prefixes)
            """)
    List<Country> findByPhoneCodes(List<String> prefixes);

}
