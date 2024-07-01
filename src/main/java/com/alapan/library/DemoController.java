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
                String status=controller.invokeMethod(methodType);
                return new ResponseEntity<>(status, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
        }
    }
}