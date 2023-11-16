package com.cbfacademy.apiassessment.utils;

import com.cbfacademy.apiassessment.model.entities.Issue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.Paths;

@Component
public class IssueConverter implements JsonConverter<Issue> {

    @Override
    public List<Issue> readJsonFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            // Define the type of the collection you want to deserialize
            Type listType = new TypeToken<List<Issue>>() {}.getType();
            // Deserialize the JSON file into a list of Issue objects
            List<Issue> issues = new Gson().fromJson(reader, listType);

            return issues;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean writeJsonFile( List<Issue> issues, String fileName ) {
        boolean status = false;

        try (FileWriter writer = new FileWriter(new File(fileName))) {
            // Serialize the list of Issue objects to JSON and write to the file
            //file.write(new Gson().toJson(issues));
            new Gson().toJson(issues, writer);
            status = true;
            // If the writing was successful, return true
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public void ReadandWrite(String inputFile, String outputFile) {
        // Read issues from the input file
        List<Issue> issues = readJsonFile(inputFile);
        // Check if the file exists
        if (Files.exists(Paths.get(inputFile))) {
            // If the file exists, overwrite it with new JSON data
            if (issues != null) {
                // Write the modified issues to the output file
                writeJsonFile(issues, outputFile);
            } else {
                System.out.println("Error reading the input file.");
            }
        }
    }
}
