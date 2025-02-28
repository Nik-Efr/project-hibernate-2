package com.javarush.entity.convertor;


import com.javarush.entity.Rating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String> {


    @Override
    public String convertToDatabaseColumn(Rating attribute) {
        return attribute.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        for(Rating rating:Rating.values()) {
            if (rating.getValue().equals(dbData)){
                return rating;
            }
        }

        return null;
    }
}
