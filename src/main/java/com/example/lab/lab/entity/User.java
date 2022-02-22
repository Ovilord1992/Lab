package com.example.lab.lab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@RequiredArgsConstructor
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String  userName;
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Crypto crypto;

}
