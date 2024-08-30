package com.example.bookingroom.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booking")
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String note;
    int totalPrice;
    boolean isCanceled;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    User user;

    @OneToMany(mappedBy = "booking")
    List<BookedRoom> bookedRooms;
}
