package com.datanavigator.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datanavigator.service.GlobalSearchService;

import java.util.Map;

@RestController
@RequestMapping("/search")
public class GlobalSearchController {

    @Autowired
    private GlobalSearchService globalSearchService;

    @GetMapping("/global")
    public Map<String, Object> globalSearch(@RequestParam String keyword) {
        return globalSearchService.search(keyword);
    }
}
