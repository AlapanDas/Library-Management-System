package com.alapan.library;

import java.util.Arrays;
import java.util.Vector;

import org.springframework.stereotype.Controller;
import com.alapan.LibraryManagement.Lib.src.DatabaseConfig.BookController;

import org.springframework.web.bind.annotation.GetMapping;
import com.alapan.LibraryManagement.Lib.src.BookConfig.Book;
import com.alapan.LibraryManagement.Lib.src.BookConfig.EmptyAttribute;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    public String driver(Book obj) {

        BookController controller = new BookController(obj);

        try {
            BookController.MethodType methodType = BookController.MethodType.valueOf("CREATE_BOOK");
            controller.invokeMethod(methodType);
            return "Book Created";
        } catch (Exception e) {
            System.out.println(e);
            return "Error";
        }
    }

    @GetMapping("/hello")
    @ResponseBody
    public String helloWorld(
            @RequestParam(name = "ISBN") String isbn,
            @RequestParam(name = "Year") int year,
            @RequestParam(name = "Author") String author,
            @RequestParam(name = "Name") String name,
            @RequestParam(name = "Publication") String publication,
            @RequestParam(name = "Tags") String tagsParam) {

        Vector<String> tags = new Vector<>(Arrays.asList(tagsParam.split(",")));

        try {
            Book obj = new Book(isbn, year, author, name, publication, tags);
            String res = driver(obj);
            System.out.println(res);
            return obj.getName();
        } catch (EmptyAttribute e) {
            System.out.println(e);
            return "Error: " + e.getMessage();
        }
    }
}