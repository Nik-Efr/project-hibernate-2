package com.javarush.entity;


import com.javarush.entity.convertor.RatingConverter;
import com.javarush.entity.convertor.SpecialFeaturesConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.time.Year;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "film",schema = "movie")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long id;

    @Column(length = 128)
    private String title;

    @Column
    private String description;

    @Column(name = "release_year")
    private Year year;

    //todo
    @Column(name = "language_id")
    private Long languageId;

    //todo
    @Column(name = "original_language_id")
    private Long originalLanguageId;

    @Column(name = "rental_duration")
    private Long rentalDuration;

    @Column(name = "rental_rate",precision = 4,scale = 2)
    private Double rentalRate;

    @Column
    private Long length;

    @Column(name = "replacement_cost",precision = 5,scale = 2)
    private Double replacementCost;

    @Column
    @Convert(converter = RatingConverter.class)
    private Rating rating;

    @Column(name = "special_features")
    @Convert(converter = SpecialFeaturesConverter.class)
    private Set<SpecialFeature> specialFeatures;

    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate;

}
