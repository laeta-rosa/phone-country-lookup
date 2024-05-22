package com.country.lookup.service.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "Country_PhoneCode",
            joinColumns = @JoinColumn(name = "country_id"),
            inverseJoinColumns = @JoinColumn(name = "phone_code_id")
    )
    private Set<PhoneCode> phoneCodes;

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

}
