package com.example.news_portal.web.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsCategoryFilter {
    @NotNull(message = "Page number must be specified!")
    @Min(value = 0, message = "Page number cannot be less than {value}")
    private Integer pageNumber;
    @NotNull(message = "Page size must be specified!")
    @Range(min = 1, max = 100, message = "Page size cannot be less than {min} and greater than {max}")
    private Integer pageSize;
}
