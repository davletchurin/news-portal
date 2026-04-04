package com.example.news_portal.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldNameConstants
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    private List<News> news = new ArrayList<>();
}
