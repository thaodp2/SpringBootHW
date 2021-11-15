package com.example.springbootclasshw1.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private String id;
    private String title;
    private String author;
    HashMap<String,BookInventory> bookInventories;
    public static Comparator<Book> bookTitleComparator = new Comparator<Book>() {

        public int compare(Book s1, Book s2) {
            String book1 = s1.getTitle().toUpperCase();
            String book2 = s2.getTitle().toUpperCase();

            //ascending order
            return book1.compareTo(book2);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }
    };
}
