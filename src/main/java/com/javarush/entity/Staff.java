package com.javarush.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.sql.Blob;

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
    private Long id;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    //todo
    @Column(name = "address_id")
    private Long addressId;

    @Lob
    @Column
    private Blob picture;

    @Column(length = 50)
    private String email;

    //todo
    @Column(name = "store_id")
    private Long storeId;

    @Column
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean active;

    @Column(length = 16)
    private String username;

    @Column(length = 40)
    private String password;

    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate;
}
