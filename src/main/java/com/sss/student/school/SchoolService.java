package com.sss.student.school;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService
{
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<SchoolResponseDto> getAllSchools() {
        return schoolRepository.findAll()
                .stream()
                .map(this::toSchoolResponseDto)
                .collect(Collectors.toList());
    }

    public SchoolResponseDto createSchool(SchoolDto schoolDto) {
        School school = toSchool(schoolDto);
        var createdSchool = schoolRepository.save(school);
        return toSchoolResponseDto(createdSchool);
    }

    private School toSchool(SchoolDto schoolDto) {
        return  new School(schoolDto.name());
    }

    private SchoolResponseDto toSchoolResponseDto(School school) {
        return  new SchoolResponseDto(school.getUuid(), school.getName());
    }
}
