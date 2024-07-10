package com.example.recruitment_service.dto.dtoOut;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageDtoOut<T> implements Serializable {

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int pageSize = 10;

    @Builder.Default
    private long totalElements = 1L;

    @Builder.Default
    private long totalPages = 1L;

    @Builder.Default
    private List<T> data = new ArrayList<>();

    public static <T> PageDtoOut<T> from(int page, int pageSize, long totalElements, List<T> data) {
        long totalPages = totalElements/pageSize;
        if(totalElements % pageSize != 0) {
            totalPages++;
        }
        return PageDtoOut.<T>builder().page(page).pageSize(pageSize).totalElements(totalElements)
                .totalPages(totalPages).data(data).build();
    }

}
