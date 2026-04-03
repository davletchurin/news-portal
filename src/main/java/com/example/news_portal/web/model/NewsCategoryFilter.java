package com.example.news_portal.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsCategoryFilter {
    private Integer pageSize;
    private Integer pageNumber;
}
