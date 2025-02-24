package com.javarush.entity.convertor;



import com.javarush.entity.SpecialFeature;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.*;
import java.util.stream.Collectors;

@Converter
public class SpecialFeaturesConverter implements AttributeConverter<Set<SpecialFeature>, String> {

    @Override
    public String convertToDatabaseColumn(Set<SpecialFeature> features) {
        if (features == null || features.isEmpty()) {
            return null;
        }

        return features.stream()
                .map(Enum::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public Set<SpecialFeature> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return Set.of();
        }

        return Arrays.stream(dbData.split(","))
                .map(String::trim)
                .map(SpecialFeature::valueOf)
                .collect(Collectors.toSet());
    }
}
