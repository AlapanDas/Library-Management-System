package com.alapan.LibraryManagement.Lib.src.UserConfig;

import com.alapan.LibraryManagement.Lib.src.BookConfig.*;;

public class UserBasic {
     public Book findBook() {
          // Book obj;
          try {
               Book obj = new Book(null, 0, null, null, null, null);
               return obj;
          } catch (Exception e) {
               e.getMessage();
               return null;
          }
     }
}
