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
@Table(name = "language", schema = "movie")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long id;

    @Column(length = 20)
    private String name;

    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate;
}
