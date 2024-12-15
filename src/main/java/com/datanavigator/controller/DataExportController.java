package com.datanavigator.controller;

import com.datanavigator.service.DataExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/data-export")
public class DataExportController {

    @Autowired
    private DataExportService dataExportService;

    @GetMapping("/export-csv")
    public StreamingResponseBody exportDataAsCSV() {
        // Fetch data to be exported
        List<Map<String, Object>> data = fetchData();
        return dataExportService.exportDataAsCSV(data);
    }

    @GetMapping("/export-pdf")
    public byte[] exportDataAsPDF() {
        // Fetch data to be exported
        List<Map<String, Object>> data = fetchData();
        return dataExportService.exportDataAsPDF(data);
    }

    private List<Map<String, Object>> fetchData() {
        // Implement logic to fetch data
        // Example data for demonstration purposes
        return List.of(
            Map.of("column1", "value1", "column2", "value2"),
            Map.of("column1", "value3", "column2", "value4")
        );
    }
}
