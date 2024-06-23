package com.imafk.tokenizer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DetokenResponse {
    private final String id;
    private final Fields fields;

    @JsonCreator
    public DetokenResponse(@JsonProperty("id") String id, @JsonProperty("fields") Fields fields) {
        this.id = id;
        this.fields = fields;
    }


    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("fields")
    public Fields getFields() {
        return fields;
    }
}
