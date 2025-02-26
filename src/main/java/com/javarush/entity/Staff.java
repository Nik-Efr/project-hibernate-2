package com.javarush.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "staff", schema = "movie")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Byte id;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Lob
    @Column(columnDefinition = "BLOB")
    private Blob picture;

    @Column(length = 50)
    private String email;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "active",columnDefinition = "BIT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isActive;

    @Column(length = 16)
    private String username;

    @Column(length = 40)
    private String password;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
