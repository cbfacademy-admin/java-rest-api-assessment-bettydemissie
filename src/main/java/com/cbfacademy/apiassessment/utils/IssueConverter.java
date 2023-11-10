package com.cbfacademy.apiassessment.utils;

import com.cbfacademy.apiassessment.model.entities.Issue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class IssueConverter {

    public static List<Issue> readJsonFile(String filePath) {
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

    public static boolean writeJsonFile( List<Issue> issues) {
        boolean status = false;
        try (FileWriter writer = new FileWriter("issues.json")) {
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
}
