package com.example.conditionalcachingdemo.controllers;

import com.example.conditionalcachingdemo.services.PlaceholderService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class PlaceholderController {

    @Autowired
    PlaceholderController(PlaceholderService placeholderService) {
        this.placeholderService = placeholderService;
    }

    private final PlaceholderService placeholderService;

    @GetMapping("/{name}")
    @Cacheable(value = "collections", condition = "#cacheables.contains(#name)")
    public JSONArray getCollection(@PathVariable String name, @Value("${api.cacheables}") List<String> cacheables) {
        return placeholderService.getCollection(name);
    }

    @GetMapping("/{name}/{id}")
    @Cacheable(value = "records", key = "#name + #id", condition = "#cacheables.contains(#name)")
    public JSONObject getRecord(@PathVariable String name, @PathVariable int id, @Value("${api.cacheables}") List<String> cacheables) {
        return placeholderService.getRecord(name, id);
    }
}
