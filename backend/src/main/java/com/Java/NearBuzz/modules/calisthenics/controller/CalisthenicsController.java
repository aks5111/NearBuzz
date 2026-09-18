package com.Java.NearBuzz.modules.calisthenics.controller;

import com.Java.NearBuzz.common.response.ApiResponse;
import com.Java.NearBuzz.modules.calisthenics.dto.response.CalisthenicsResponse;
import com.Java.NearBuzz.modules.calisthenics.service.CalisthenicsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/calisthenics")
public class CalisthenicsController {

    private final CalisthenicsService service;

    public CalisthenicsController(CalisthenicsService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<CalisthenicsResponse>> list(@RequestParam(required = false) String search) {
        return ApiResponse.ok("Calisthenics listings fetched", service.listPublished(search));
    }
}
