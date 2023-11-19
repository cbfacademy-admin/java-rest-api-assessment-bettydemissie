package com.cbfacademy.apiassessment.utils;

import com.cbfacademy.apiassessment.model.entities.Employee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
@Component
public class EmployeeConverter implements JsonConverter<Employee> {
    private static final Logger log = LoggerFactory.getLogger(EmployeeConverter.class);
    @Override
    public List<Employee> readJsonFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<Employee>>() {
            }.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
            log.error("Unable to read the file.");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean writeJsonFile(List<Employee> employees, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            new Gson().toJson(employees, writer);
            return true;
        } catch (IOException e) {
            log.error("Unable to write the file.");
            e.printStackTrace();
            return false;
        }
    }

}
