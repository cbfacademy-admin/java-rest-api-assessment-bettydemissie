package com.cbfacademy.apiassessment.utils;

import com.cbfacademy.apiassessment.model.entities.Employee;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
@Component
public class EmployeeConverter implements JsonConverter<Employee> {

    @Override
    public List<Employee> readJsonFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<List<Employee>>() {
            }.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {
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
            e.printStackTrace();
            return false;
        }
    }

    public void ReadandWrite(String inputFile, String outputFile) {
        // Read issues from the input file
        List<Employee> employees = readJsonFile(inputFile);

        if (employees != null) {
            // Modify the issues or perform any necessary operations

            // Write the modified issues to the output file
            writeJsonFile(employees, outputFile);
        } else {
            System.out.println("Error reading the input file.");
        }
    }
}
