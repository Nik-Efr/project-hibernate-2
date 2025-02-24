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
@Table(name = "address", schema = "movie")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column(length = 50)
    private String address;

    @Column(length = 50)
    private String address2;

    @Column(length = 20)
    private String district;

    //todo
    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "postal_code",length = 20)
    private String postalCode;

    @Column(length = 20)
    private String phone;

    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate;

}
