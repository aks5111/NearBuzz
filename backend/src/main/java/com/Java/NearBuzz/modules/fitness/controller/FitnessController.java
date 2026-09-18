package com.Java.NearBuzz.modules.fitness.controller;

import com.Java.NearBuzz.common.response.ApiResponse;
import com.Java.NearBuzz.modules.fitness.dto.response.FitnessResponse;
import com.Java.NearBuzz.modules.fitness.service.FitnessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fitness")
public class FitnessController {

    private final FitnessService service;

    public FitnessController(FitnessService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<FitnessResponse>> list(@RequestParam(required = false) String search) {
        return ApiResponse.ok("Fitness listings fetched", service.listPublished(search));
    }
}
