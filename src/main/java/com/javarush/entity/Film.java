package com.javarush.entity;


import com.javarush.entity.convertor.RatingConverter;
import com.javarush.entity.convertor.SpecialFeaturesConverter;
import com.mysql.cj.protocol.ColumnDefinition;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.boot.model.source.spi.IdentifierSource;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
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
    private Short id;

    @Column(length = 128)
    private String title;

    @Column(columnDefinition = "text")
    @Type(type = "text")
    private String description;

    //todo convert
    @Column(name = "release_year",columnDefinition = "year")
    private Year year;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration")
    private Byte rentalDuration;

    @Column(name = "rental_rate",precision = 4,scale = 2)
    private BigDecimal rentalRate;

    private Short length;

    @Column(name = "replacement_cost",precision = 5,scale = 2)
    private BigDecimal replacementCost;

    @Column(columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    @Convert(converter = RatingConverter.class)
    private Rating rating;

    @Column(name = "special_features",columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    @Convert(converter = SpecialFeaturesConverter.class)
    private Set<SpecialFeature> specialFeatures;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @ManyToMany
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id",referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id",referencedColumnName = "actor_id")
    )
    List<Actor> actors;

    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id",referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    )
    List<Category> categories;
}
