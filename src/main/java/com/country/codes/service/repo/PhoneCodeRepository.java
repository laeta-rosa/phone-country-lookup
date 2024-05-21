package com.country.codes.service.repo;

import com.country.codes.service.model.PhoneCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhoneCodeRepository extends JpaRepository<PhoneCode, Long> {

    PhoneCode findByCode(String code);

}
