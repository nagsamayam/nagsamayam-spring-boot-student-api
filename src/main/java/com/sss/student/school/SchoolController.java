package com.sss.student.school;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("")
    public List<SchoolResponseDto> index() {
        return schoolService.getAllSchools();
    }

    @PostMapping
    public SchoolResponseDto store(@RequestBody SchoolDto schoolDto) {
        return schoolService.createSchool(schoolDto);
    }
}
