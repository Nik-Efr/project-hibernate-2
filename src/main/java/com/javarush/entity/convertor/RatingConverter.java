package com.javarush.entity.convertor;


import com.javarush.entity.Rating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RatingConverter implements AttributeConverter<Rating, String> {

    @Override
    public String convertToDatabaseColumn(Rating rating) {
        return rating != null ? rating.getDisplayName() : null;
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        return dbData != null ? Rating.fromDisplayName(dbData) : null;
    }
}
