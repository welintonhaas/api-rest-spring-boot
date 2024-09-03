package org.example.cursospringboot.rest.controller;

import lombok.Data;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
@Data
public class ApiErrors {
    private List<String> errors;

    public ApiErrors(String message) {
        this.errors = Collections.singletonList(message);
    }

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }
}
