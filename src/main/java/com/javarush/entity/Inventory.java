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
@Table(name = "inventory",schema = "movie")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long id;

    //todo
    @Column(name = "film_id")
    private Long filmId;

    //todo
    @Column(name = "store_id")
    private Long storeId;

    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate;

}
