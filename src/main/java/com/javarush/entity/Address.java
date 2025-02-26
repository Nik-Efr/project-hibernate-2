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
@Table(name = "address", schema = "movie")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Short id;

    @Column(length = 50)
    private String address;

    @Column(length = 50)
    private String address2;

    @Column(length = 20)
    private String district;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "postal_code",length = 20)
    private String postalCode;

    @Column(length = 20)
    private String phone;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}
