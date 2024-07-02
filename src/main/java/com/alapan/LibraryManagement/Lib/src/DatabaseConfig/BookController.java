package com.alapan.LibraryManagement.Lib.src.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import com.alapan.LibraryManagement.Lib.src.BookConfig.Book;
import com.alapan.LibraryManagement.Lib.src.BookConfig.EmptyAttribute;
import com.google.gson.*;

public class BookController {
     // Global Variables
     String url = "jdbc:postgresql://aws-0-us-east-1.pooler.supabase.com:6543/postgres";
     String user = "postgres.sgkcxzcwstcejwxjgigw";
     String password = "46qVYfQ3j3Hj";
     private Book obj;

     // Creating a connection object to initialize connection
     Connection conn = null;;
     // Creates a precompiled sql statement and replaces variable with '?'
     PreparedStatement pstmt = null;
     // Converts Object-> JSON (by Google)
     Gson gson = new Gson();

     public enum MethodType {
          CREATE_BOOK, DELETE_BOOK, SEARCH_BOOK
     }

     public BookController(Book obj) {
          this.obj = obj;
     }

     private String CreateBook() {

          // Extracting the parameters from the object
          Vector<String> tags = obj.getTags();
          String tagsJson = gson.toJson(tags);

          String ISBN = obj.getISBN();
          String Name = obj.getName();
          String Publication = obj.getPublication();
          int Year = obj.getYear();
          String Author = obj.getAuthor();

          try {
               conn = DriverManager.getConnection(url, user, password);

               if (!conn.isClosed())
                    System.out.println("Connected for Creation");

               String sql = "INSERT INTO lib (\"ISBN\", \"Name\", \"Author\", \"Year\", \"Publication\", \"Tags\") VALUES (?, ?, ?, ?, ?, CAST(? AS JSON))";
               pstmt = conn.prepareStatement(sql);
               pstmt.setString(1, ISBN);
               pstmt.setString(2, Name);
               pstmt.setString(3, Author);
               pstmt.setInt(4, Year);
               pstmt.setString(5, Publication);
               pstmt.setString(6, tagsJson);
               pstmt.executeUpdate();

               return "Book stored successfully in the database.";

          } catch (SQLException ex) {
               return ex.getMessage();

          } finally {
               // Close the resources
               try {
                    if (pstmt != null)
                         pstmt.close();
                    if (conn != null)
                         conn.close();
               } catch (SQLException ex) {
                    ex.printStackTrace();
               }
          }
     }

     private String DeleteBook() {

          // Since we only require Primary Key to delete the record, we need only ISBN Number


          String ISBN = obj.getISBN();
          try {
               conn = DriverManager.getConnection(url, user, password);

               if (!conn.isClosed())
                    System.out.println("Connected for Deletion");

               String sql = "DELETE FROM public.lib WHERE \"ISBN\" = ?";
               pstmt = conn.prepareStatement(sql);
               pstmt.setString(1, ISBN);
               pstmt.executeUpdate();
               return ("Book deleted successfully from the database.");

          } catch (Exception e) {
               e.printStackTrace();
               return (e.getMessage());

          } finally {
               try {
                    if (pstmt != null)
                         pstmt.close();
                    if (conn != null)
                         conn.close();
               } catch (SQLException ex) {
                    ex.printStackTrace();
               }
          }
     }

     private String SearchBook() {
          String ISBN = obj.getISBN();
          try {
               conn = DriverManager.getConnection(url, user, password);
               if (!conn.isClosed())
                    System.out.println("Connected for Searching");

               String sql = "SELECT * FROM public.lib WHERE \"ISBN\" = ?";
               pstmt = conn.prepareStatement(sql);
               pstmt.setString(1, ISBN);
               ResultSet rs = pstmt.executeQuery();

               if (rs.next()) {
                    String isbn = rs.getString("ISBN");
                    int year = rs.getInt("Year");
                    String author = rs.getString("Author");
                    String name = rs.getString("Name");
                    String publication = rs.getString("Publication");
                    String tagsJson = rs.getString("Tags");
                    @SuppressWarnings("unchecked")
                    Vector<String> tags = gson.fromJson(tagsJson, Vector.class);

                    Book foundBook = new Book(isbn, year, author, name, publication, tags);
                    String bookJson = gson.toJson(foundBook);

                    return bookJson;
               } else {
                    return ("Book not found");
               }

          } catch (Exception e) {
               e.printStackTrace();
               return (e.getMessage());

          } finally {
               try {
                    if (pstmt != null)
                         pstmt.close();
                    if (conn != null)
                         conn.close();
               } catch (SQLException ex) {
                    ex.printStackTrace();
               }
          }
     }

     public String invokeMethod(MethodType method) throws EmptyAttribute {
          switch (method) {
               case CREATE_BOOK:
                    String cb = CreateBook();
                    return cb;
               case DELETE_BOOK:
                    String db = DeleteBook();
                    return db;
               case SEARCH_BOOK:
                    String sb = SearchBook();
                    return sb;
               default:
                    return "";
          }
     }

}