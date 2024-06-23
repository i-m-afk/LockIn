package com.imafk.tokenizer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Field {
    private final Boolean found;
    private final String value;

    @JsonCreator
    public Field(@JsonProperty("found") Boolean found, @JsonProperty("value") String value) {
        this.found = found;
        this.value = value;
    }

    @JsonProperty("found")
    public Boolean getFound() {
        return found;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }
}
