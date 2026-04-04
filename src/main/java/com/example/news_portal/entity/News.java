package com.example.news_portal.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "news")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@FieldNameConstants
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private NewsCategory category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    List<Comment> comments = new ArrayList<>();

    @CreationTimestamp
    private Instant createAt;

    @UpdateTimestamp
    private Instant updateAt;
}
