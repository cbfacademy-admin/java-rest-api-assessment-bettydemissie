package com.cbfacademy.apiassessment;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter{
    private ObjectMapper objectMapper;

    public JsonConverter() {
        objectMapper = new ObjectMapper();
    }

    public <T> T fromJson(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void printJson(Object object) {
        String json = toJson(object);
        if (json != null) {
            System.out.println(json);
        } else {
            System.out.println("Failed to convert to JSON.");
        }
    }
}
