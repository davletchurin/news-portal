package com.example.news_portal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "news_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NewsCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    List<News> news = new ArrayList<>();
}
