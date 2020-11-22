package com.example.conditionalcachingdemo.services;

import com.example.conditionalcachingdemo.exceptions.CollectionNotFoundException;
import com.example.conditionalcachingdemo.exceptions.RecordNotFoundException;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
class PlaceholderServiceImpl implements PlaceholderService {

    @Autowired
    PlaceholderServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    private final RestTemplate restTemplate;

    private final String apiUrl;

    @Override
    public JSONArray getCollection(String name) throws InterruptedException {
        try {
            JSONArray collection = restTemplate.getForObject(collectionUrl(name), JSONArray.class);
            Thread.sleep(3000); // Simulating a slow network.
            return collection;
        } catch (HttpClientErrorException.NotFound e) {
            throw new CollectionNotFoundException();
        }
    }

    @Override
    public JSONObject getRecord(String name, int id) throws InterruptedException {
        try {
            JSONObject record = restTemplate.getForObject(recordUrl(name, id), JSONObject.class);
            Thread.sleep(3000); // Simulating a slow network.
            return record;
        } catch (HttpClientErrorException.NotFound e) {
            throw new RecordNotFoundException();
        }
    }

    private String collectionUrl(String name) {
        return apiUrl + "/" + name;
    }

    private String recordUrl(String name, int id) {
        return collectionUrl(name) + "/" + id;
    }
}

