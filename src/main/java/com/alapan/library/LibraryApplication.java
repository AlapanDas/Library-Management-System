package com.alapan.library;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.alapan.LibraryManagement.Lib.src.BookConfig.EmptyAttribute;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) throws EmptyAttribute {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println("Application is shutting down...");
		}));

		SpringApplication.run(LibraryApplication.class, args);

	}

}
