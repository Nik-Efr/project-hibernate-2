package com.javarush.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rental", schema = "movie")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Long id;

    @Column(name = "rental_date")
    private LocalDateTime rentalDate;

    //todo
    @Column(name = "inventory_id")
    private Long inventoryId;

    //todo
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    //todo
    @Column(name = "staff_id")
    private Long staffId;

    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate;
}
