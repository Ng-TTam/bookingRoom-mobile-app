package com.example.bookingroom.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "hotel")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    float starLevel;
    String address;
    String description;
    String image;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    List<Room> rooms;
}
