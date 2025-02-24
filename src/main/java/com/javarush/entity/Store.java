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
@Table(name = "store", schema = "movie")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    //todo
    @Column(name = "manager_staff_id")
    private Long managerStaffId;

    //todo
    @Column(name = "address_id")
    private Long addressId;

    @UpdateTimestamp
    @Column(name = "last_update")
    private Timestamp lastUpdate;
}
