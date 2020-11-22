package com.example.conditionalcachingdemo.controllers;

import com.example.conditionalcachingdemo.services.PlaceholderService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PlaceholderController {

    @Autowired
    PlaceholderController(PlaceholderService placeholderService) {
        this.placeholderService = placeholderService;
    }

    private final PlaceholderService placeholderService;

    @GetMapping("/{name}")
    public JSONArray getCollection(@PathVariable String name) {
        return placeholderService.getCollection(name);
    }

    @GetMapping("/{name}/{id}")
    public JSONObject getRecord(@PathVariable String name, @PathVariable int id) {
        return placeholderService.getRecord(name, id);
    }
}
