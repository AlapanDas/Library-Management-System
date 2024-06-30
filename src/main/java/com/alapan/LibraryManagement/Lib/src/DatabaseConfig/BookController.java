package com.alapan.LibraryManagement.Lib.src.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import com.alapan.LibraryManagement.Lib.src.BookConfig.Book;
import com.alapan.LibraryManagement.Lib.src.BookConfig.EmptyAttribute;
import com.google.gson.*;

public class BookController {
     // Global Variables
     final String url = "jdbc:mysql://localhost/library?user=root&password=6969";
     private Book obj;
     // Creating a connection object to initialize connection
     Connection conn = null;;
     // Creates a precompiled sql statement and replaces variable with '?'
     PreparedStatement pstmt = null;
     // Converts Object-> JSON (by Google)
     Gson gson = new Gson();

     public enum MethodType {
          CREATE_BOOK, DELETE_BOOK
     }

     public BookController(Book obj) {
          this.obj = obj;
     }

     private void CreateBook() {

          // Extracting the parameters from the object
          Vector<String> tags = obj.getTags();
          String tagsJson = gson.toJson(tags);

          String ISBN = obj.getISBN();
          String Name = obj.getName();
          String Publication = obj.getPublication();
          int Year = obj.getYear();
          String Author = obj.getAuthor();

          try {
               conn = DriverManager.getConnection(url);

               if (!conn.isClosed())
                    System.out.println("Connected");

               String sql = "INSERT INTO lib (ISBN, Name, Author, Year, Publication, Tags) VALUES (?, ?, ?, ?, ?, ?)";
               pstmt = conn.prepareStatement(sql);
               pstmt.setString(1, ISBN);
               pstmt.setString(2, Name);
               pstmt.setString(3, Author);
               pstmt.setInt(4, Year);
               pstmt.setString(5, Publication);
               pstmt.setString(6, tagsJson);
               pstmt.executeUpdate();

               // while (rs.next()) {
               // String id = rs.getString("ROLL");
               // String name = rs.getString("FIRST_NAME");
               // String last = rs.getString("LAST_NAME");
               // String email = rs.getString("EMAIL");
               // System.out.println("ID: " + id + ", Name: " + name + last + " " + ", Email: "
               // + email);
               // }
               System.out.println("Book stored successfully in the database.");

          } catch (SQLException ex) {
               ex.printStackTrace();
               // System.out.println("SQLException: " + ex.getMessage());
               // System.out.println("SQLState: " + ex.getSQLState());
               // System.out.println("VendorError: " + ex.getErrorCode());

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

     private void DeleteBook() {

          // Since we only require Primary Key to delete the record, we need only ISBN
          // Number
          String ISBN = obj.getISBN();

          try {
               conn = DriverManager.getConnection(url);

               if (!conn.isClosed())
                    System.out.println("Connected");

               String sql = "DELETE FROM lib WHERE ISBN=?";
               pstmt = conn.prepareStatement(sql);
               pstmt.setString(1, ISBN);
               pstmt.executeUpdate();
               System.out.println("Book deleted successfully from the database.");

          } catch (Exception e) {
               System.out.println(e);

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

     public void invokeMethod(MethodType method) throws EmptyAttribute {
          switch (method) {
               case CREATE_BOOK:
                    CreateBook();
                    break;
               case DELETE_BOOK:
                    DeleteBook();
                    break;
               default:
                    break;
          }
     }
}