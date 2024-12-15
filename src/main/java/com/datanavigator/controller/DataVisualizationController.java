package com.datanavigator.controller;

import com.datanavigator.service.DataVisualizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/data-visualization")
public class DataVisualizationController {

    @Autowired
    private DataVisualizationService dataVisualizationService;

    @GetMapping("/chart-data")
    public List<Map<String, Object>> getChartData() {
        return dataVisualizationService.getChartData();
    }

    @GetMapping("/custom-report")
    public byte[] generateReport(@RequestParam String criteria) {
        return dataVisualizationService.generateReport(criteria);
    }
}
