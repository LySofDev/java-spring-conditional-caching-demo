package com.example.conditionalcachingdemo.services;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public interface PlaceholderService {
    JSONArray getCollection(String name) throws InterruptedException;
    JSONObject getRecord(String name, int id) throws InterruptedException;
}
