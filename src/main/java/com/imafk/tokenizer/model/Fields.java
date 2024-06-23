package com.imafk.tokenizer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;

public class Fields {
    private LinkedHashMap<String, Field> data = new LinkedHashMap<>();

    @JsonCreator
    public Fields(@JsonProperty("data") LinkedHashMap<String, Field> data) {
        this.data = data;
    }

    @JsonProperty("data")
    public LinkedHashMap<String, Field> getData() {
        return data;
    }

}

