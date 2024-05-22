package com.country.lookup.service.parser;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class PhoneCodesParser {

    /**
     * Parses a string of phone codes and returns a set of the parsed codes.
     * As part of pre-processing removes all non-digit characters except parentheses and comma for splitting.
     * Handles different formats of phone codes, including those with prefix and codes separated by parentheses and comma.
     *
     * @param codesString the string of phone codes to parse
     * @return a set of parsed phone codes
     */
    public Set<String> parsePhoneCodes(final String codesString) {
        Set<String> result = new HashSet<>();
        String cleaned = codesString.replaceAll("[^\\d(), ]", "");

        String[] parts = cleaned.trim().split("\\)");

        for (String part : parts) {
            String trimmedPart = part.trim();
            if (!trimmedPart.isEmpty()) {
                if (trimmedPart.contains("(")) {
                    // if part contains parentheses, split by '(' and process prefix and code separately
                    String[] subParts = trimmedPart.split("\\(");
                    String prefix = subParts[0].replaceAll(",", "").trim();
                    String[] codes = subParts[1].trim().split(",");
                    for (String code : codes) {
                        if (!code.isEmpty()) {
                            result.add(prefix + code.trim());
                        }
                    }
                } else {
                    // if part contains no parentheses, remove comma and spaces, and add directly
                    trimmedPart = trimmedPart.replaceAll(",", "").trim();
                    result.add(trimmedPart);
                }
            }
        }
        return result;
    }

}
