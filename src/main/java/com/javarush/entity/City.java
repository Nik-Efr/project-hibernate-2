package com.javarush.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "city", schema = "movie")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long id;

    @Column(length = 50)
    private String city;

    //todo
    @Column(name = "city_id")
    private Long countryId;

    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate;
}
