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
@Table(name = "payment", schema = "movie")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    //todo
    @Column(name = "customer_id")
    private Long customerId;

    //todo
    @Column(name = "staff_id")
    private Long staffId;

    //todo
    @Column(name = "rental_id")
    private Long rentalId;

    @Column(precision = 5,scale = 2)
    private Double amount;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate;
}
