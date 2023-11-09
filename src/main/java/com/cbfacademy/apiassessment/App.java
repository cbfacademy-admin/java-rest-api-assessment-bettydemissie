package com.cbfacademy.apiassessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		JsonConverter converter = new JsonConverter();
		String json = FileUtils.readFile(filePath);
	}

}
