package com.cbfacademy.apiassessment.utils;
import java.util.List;

public interface JsonConverter<T> {

    List<T> readJsonFile(String filePath);

    boolean writeJsonFile(List<T> items, String filePath);

    void ReadandWrite(String inputFile, String outputFile);

}
