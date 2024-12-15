package com.datanavigator.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datanavigator.service.DashboardService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/summary")
    public Map<String, Object> getDashboardSummary() {
        Map<String, Object> summary = new HashMap<>();
        summary.put("totalCustomers", dashboardService.getTotalCustomers());
        summary.put("totalProducts", dashboardService.getTotalProducts());
        summary.put("totalTransactions", dashboardService.getTotalTransactions());
        summary.put("recentActivities", dashboardService.getRecentActivities());
        return summary;
    }
}
