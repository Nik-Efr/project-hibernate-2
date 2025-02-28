package com.javarush.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "film_text",schema = "movie")
public class FilmText {
    @Id
    @Column(name = "film_id")
    private Short id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(length = 255)
    private String title;

    @Column(columnDefinition = "text")
    @Type(type = "text")
    private String description;
}
