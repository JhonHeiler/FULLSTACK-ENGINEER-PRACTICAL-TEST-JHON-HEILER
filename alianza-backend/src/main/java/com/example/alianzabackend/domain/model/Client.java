package com.example.alianzabackend.domain.model;
import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sharedKey;

    private String name;

    private String email;

    private String phone;

    private LocalDate startDate;

    private LocalDate endDate;

}
