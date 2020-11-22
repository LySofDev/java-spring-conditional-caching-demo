package com.example.conditionalcachingdemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Collection not found.")
public class CollectionNotFoundException extends RuntimeException {}
