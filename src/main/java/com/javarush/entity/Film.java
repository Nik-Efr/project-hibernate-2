package com.javarush.entity;


import com.javarush.entity.convertor.RatingConverter;
import com.javarush.entity.convertor.YearConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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


    @Column(name = "release_year",columnDefinition = "year")
    @Convert(converter = YearConverter.class)
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
    private String specialFeatures;

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

    public Set<SpecialFeature> getSpecialFeatures() {
        if (specialFeatures == null || specialFeatures.isEmpty()){
            return null;
        }
        Set<SpecialFeature> set = new HashSet<SpecialFeature>();
        for (String feature : specialFeatures.split(",")){
            set.add(SpecialFeature.valueOf(feature.trim()));
        }
        return set;
    }

    public void setSpecialFeatures(Set<SpecialFeature> specialFeatures) {
        if (specialFeatures == null){
            this.specialFeatures = null;
            return;
        }else{
            this.specialFeatures = specialFeatures.stream().map(SpecialFeature::getValue).collect(Collectors.joining(","));
        }
    }
}
