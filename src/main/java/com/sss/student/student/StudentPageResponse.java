package com.sss.student.student;


import lombok.Builder;

import java.util.List;

@Builder
public record StudentPageResponse(
        List<StudentResponseDto> students,
        Integer page,
        Integer perPage,
        long totalElements,
        int totalPages,
        int countInCurrentPage,
        boolean hasMore) {
}
