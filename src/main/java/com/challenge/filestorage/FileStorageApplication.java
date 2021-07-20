package com.challenge.filestorage;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class FileStorageApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(FileStorageApplication.class).run(args);
	}

}
