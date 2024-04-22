package com.example.bookingroom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "hotel")
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int starLevel;

    @Column(nullable = false)
    private String address;

    @Column
    private String description;

    @Column
    private String image;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;
}
