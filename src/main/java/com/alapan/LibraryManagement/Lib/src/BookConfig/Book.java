package com.alapan.LibraryManagement.Lib.src.BookConfig;

import java.util.Vector;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {
    @JsonProperty("ISBN")
    private String ISBN;
    @JsonProperty("Year")
    private int Year;
    @JsonProperty("Author")
    private String Author;
    @JsonProperty("Name")
    private String Name;
    @JsonProperty("Publication")
    private String Publication;
    @JsonProperty("Tags")
    private Vector<String> Tags;


    public Book() {
    }

    public Book(String ISBN, int year, String author, String name, String publication, Vector<String> tags) {
        this.ISBN = ISBN;
        Year = year;
        Author = author;
        Name = name;
        Publication = publication;
        Tags = tags;
    }

    // Getters and setters for each field
    public String getISBN() {
        return ISBN.toString();
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN.toString();
    }

    public int getYear() {
        return Integer.valueOf(Year);
    }

    public void setYear(int year) {
        Year = Integer.valueOf(year);
    }

    public String getAuthor() {
        return Author.toString();
    }

    public void setAuthor(String author) {
        Author = author.toString();
    }

    public String getName() {
        return Name.toString();
    }

    public void setName(String name) {
        Name = name.toString();
    }

    public String getPublication() {
        return Publication.toString();
    }

    public void setPublication(String publication) {
        Publication = publication.toString();
    }

    public Vector<String> getTags() {
        return Tags;
    }

    public void setTags(Vector<String> tags) {
        Tags = tags;
    }
}
