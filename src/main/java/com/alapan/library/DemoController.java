package com.alapan.library;

import com.alapan.LibraryManagement.Lib.src.BookConfig.Book;
import com.alapan.LibraryManagement.Lib.src.DatabaseConfig.BookController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class DemoController {

    @PostMapping("/create")
    public ResponseEntity<String> createBook(@RequestBody String bookJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Book obj = mapper.readValue(bookJson, Book.class);
        BookController controller = new BookController(obj);

        try {
            BookController.MethodType methodType = BookController.MethodType.valueOf("CREATE_BOOK");

            try {
                final String status = controller.invokeMethod(methodType);
                return new ResponseEntity<>(status, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteBook(@RequestBody String bookJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Book obj = mapper.readValue(bookJson, Book.class);
        BookController controller = new BookController(obj);

        try {
            BookController.MethodType methodType = BookController.MethodType.valueOf("DELETE_BOOK");

            try {
                final String status = controller.invokeMethod(methodType);
                return new ResponseEntity<>(status, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<String> searchBook(@RequestBody String bookJson) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Book obj = mapper.readValue(bookJson, Book.class);
        BookController controller = new BookController(obj);

        try {
            BookController.MethodType methodType = BookController.MethodType.valueOf("SEARCH_BOOK");

            try {
                final String status = controller.invokeMethod(methodType);
                return new ResponseEntity<>(status, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
        }
    }

    @GetMapping("/getBooks")
    public ResponseEntity<String> getBooks() throws JsonProcessingException {
        BookController controller = new BookController(null);
        try {

            final String results = controller.GetBook();
            return new ResponseEntity<>(results, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.CONFLICT);
    }
}