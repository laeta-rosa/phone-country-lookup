package com.country.codes.service.repo;

import com.country.codes.service.model.CountryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryCodeRepository extends JpaRepository<CountryCode, Long> {

    @Query("SELECT c FROM CountryCode c WHERE :prefix LIKE CONCAT(c.countryCode, '%')")
    List<CountryCode> findByPrefix(String prefix);

    @Query("SELECT c FROM CountryCode c WHERE c.countryCode IN :prefixes ORDER BY LENGTH(c.countryCode) DESC")
    List<CountryCode> findByCountryCodes(List<String> prefixes);

}
