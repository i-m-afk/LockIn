package com.imafk.tokenizer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public final class TokenRequest {
    private final String id;
    private final Data data;

    @JsonCreator
    public TokenRequest(@JsonProperty("id") String id, @JsonProperty("data") Data data) {
        this.id = id;
        this.data = data;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        TokenRequest that = (TokenRequest) obj;
        return Objects.equals(this.id, that.id) && Objects.equals(this.data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data);
    }

    @Override
    public String toString() {
        return "TokenRequest[" + "id=" + id + ", " + "data=" + data + ']';
    }
}
