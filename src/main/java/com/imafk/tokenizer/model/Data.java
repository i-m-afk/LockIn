package com.imafk.tokenizer.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.LinkedHashMap;
import java.util.Objects;

public class Data {
    private final LinkedHashMap<String, String> data;

    @JsonCreator
    public Data(@JsonProperty("data") LinkedHashMap<String, String> data) {
        this.data = data != null ? data : new LinkedHashMap<>();
    }

    @JsonAnyGetter
    public LinkedHashMap<String, String> getData() {
        return data;
    }

    @JsonAnySetter
    public void setData(String key, String value) {
        data.put(key, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Data that = (Data) obj;
        return Objects.equals(this.data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "Data[" + "data=" + data + ']';
    }
}
