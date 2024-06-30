
package com.alapan.LibraryManagement.Lib.src.BookConfig;

import java.util.Vector;

public class Book {
     private String ISBN;
     private String Name;
     private String Author;
     private int Year;
     private String Publication;
     private Vector<String> Tags = new Vector<String>();

     public String getISBN() {
          return ISBN;
     }

     public String getName() {
          return Name;
     }

     public String getAuthor() {
          return Author;
     }

     public int getYear() {
          return Year;
     }

     public String getPublication() {
          return Publication;
     }

     public Vector<String> getTags() {
          return Tags;
     }

     // Constructor to initialize the Book object
     public Book(String isbn, int year, String author, String name, String publication, Vector<String> tags)
               throws EmptyAttribute {
          if (isbn == null || isbn.trim().isEmpty() ||
                    author == null || author.trim().isEmpty() ||
                    name == null || name.trim().isEmpty() ||
                    publication == null || publication.trim().isEmpty() ||
                    tags.size() == 0) {
               throw new EmptyAttribute();
          }
          this.ISBN = isbn;
          this.Year = year;
          this.Author = author;
          this.Name = name;
          this.Publication = publication;
          this.Tags = (Vector<String>) tags.clone();
     }

     public void getDetails() {
          System.out.println("   ::Book Details::");
          System.out.println("------------------------");
          System.out.println("Name:   " + Name);
          System.out.println("Author: " + Author);
          System.out.println("Year:   " + Year);
          System.out.println("ISBN:   " + ISBN);
          for (String string : Tags) {
               System.out.print(string + " ");
          }
          System.out.println();
          System.out.println("Publication:  " + Publication);
          System.out.println("------------------------");
     }

     public void ChangeTags(Vector<String> tags) {
          for (int i = 0; i < tags.size(); i++) {
               if (!Tags.contains(tags.elementAt(i)))
                    Tags.addElement(tags.elementAt(i));
          }
          System.out.println("Successfully Updated\n");
     }
}
