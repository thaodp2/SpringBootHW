package com.example.springbootclasshw1.service;


import com.example.springbootclasshw1.annotation.Benchmark;
import com.example.springbootclasshw1.model.Book;
import com.example.springbootclasshw1.model.BookInventory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import com.github.javafaker.Faker;
@Service
public class BookService {

    public List<Book> books =getAllListBooks();
    public List<Book> getAllListBooks(){
        List<Book> books = new ArrayList<>();
        Book book = new Book();
        for (int i = 0; i < 1000; i++) {
            Faker faker = new Faker();
            String id = String.valueOf(i+1);
            String author = faker.book().author();
            String title = faker.book().title();
            books.add(new Book(id,title,author,getAllListBooksInventory(id)));
        }
        return books;
    }
    public HashMap<String, BookInventory> getAllListBooksInventory(String idBook){
        HashMap<String,BookInventory> bookInventories =new HashMap<String,BookInventory>();
        Faker faker = new Faker();
        int amount = faker.number().numberBetween(0,100);
        LocalDateTime time = LocalDateTime.now();
        bookInventories.put(idBook,new BookInventory(1,idBook,amount,time));
        return bookInventories;
    }

    public List<Book> getAllListBooksSortByTitle(){
        List<Book> books = getAllListBooks();
        Collections.sort(books,Book.bookTitleComparator);
        return books;
    }

    public List<Book> getAllListBookHaveAmountZero() {
        List<Book> books = getAllListBooks();
        List<Book> booksByAmount = new ArrayList<>();

        for (Book book : books) {
            if (book.getBookInventories().get(book.getId()).getAmount() == 0) {
                booksByAmount.add(book);
            }
        }


        return booksByAmount;

    }
    public List<Book> getAllListBookSearchByTitle(String title){
//        List<Book> books =getAllListBooks();
        List<Book> booksByTitle = new ArrayList<>();
        for (Book book:books
        ){if(book.getTitle().contains(title)){
            booksByTitle.add(book);
        }
        }
        return booksByTitle;
    }
    public String buyBook(String idBook, int numberOffBook){
        for (Book book: books
        ) {
            if (idBook.equals(book.getId())) {
                if (book.getBookInventories().get(book.getId()).getAmount() == 0) {
                    return "Books with amount = 0";
                }else if (book.getBookInventories().get(book.getId()).getAmount() < numberOffBook) {
                    return "The number of books purchased is more than the number of books available, amount = "+book.getBookInventories().get(book.getId()).getAmount();
                }else{
                    int numberAmount = book.getBookInventories().get(book.getId()).getAmount()-numberOffBook;
                    LocalDateTime time = LocalDateTime.now();
                    BookInventory bookInventory = new BookInventory(1,book.getId(),numberAmount,time);
                    HashMap<String,BookInventory> stringBookInventoryBHashMap = new HashMap<String, BookInventory>();
                    stringBookInventoryBHashMap.put(book.getId(),bookInventory);
                    book.setBookInventories(stringBookInventoryBHashMap);
                    return "Buy "+numberOffBook +" book is "+book.getTitle();
                }
            }
        }
        return "Book doesn't exist";
    }

    public String orderBook(String idBook, int numberBookOrder){
        for (Book book: books
        ) {
            if (idBook.equals(book.getId())) {
                int numberAmount = book.getBookInventories().get(book.getId()).getAmount()+numberBookOrder;
                LocalDateTime time = LocalDateTime.now();
                BookInventory bookInventory = new BookInventory(1,book.getId(),numberAmount,time);
                HashMap<String,BookInventory> stringBookInventoryBHashMap = new HashMap<String, BookInventory>();
                stringBookInventoryBHashMap.put(book.getId(),bookInventory);
                book.setBookInventories(stringBookInventoryBHashMap);
                return "Order "+numberBookOrder +" book is "+book.getTitle()+" sussces!";
            }
        }
        return "Book doesn't exist";
    }

    private List<Book> listBookAmountOnly1(){
        List<Book> listBookHaveAmountOnly1 = new ArrayList<>();
        for (Book book: books
        ) {
            if (book.getBookInventories().get(book.getId()).getAmount()==1) {
                listBookHaveAmountOnly1.add(book);
            }
        }
        return listBookHaveAmountOnly1;
    }
    public String orderBookInListBookAmount1(int numberBook, String idBook){
        List<Book> listBookHaveAmountOnly1 =listBookAmountOnly1();
        if(numberBook!=5){
            for (Book book: listBookHaveAmountOnly1
            ) {
                if (book.getId().equals(idBook)) {
                    int numberAmount = book.getBookInventories().get(book.getId()).getAmount()+numberBook;
                    LocalDateTime time = LocalDateTime.now();
                    BookInventory bookInventory = new BookInventory(1,book.getId(),numberAmount,time);
                    HashMap<String,BookInventory> stringBookInventoryBHashMap = new HashMap<String, BookInventory>();
                    stringBookInventoryBHashMap.put(book.getId(),bookInventory);
                    book.setBookInventories(stringBookInventoryBHashMap);
                    return "order book: "+book.getTitle()+"have number: "+numberBook+" book susscess!";
                }
            }
        }
        for (Book book: listBookHaveAmountOnly1
        ) {
            int numberAmount = book.getBookInventories().get(book.getId()).getAmount()+numberBook;
            LocalDateTime time = LocalDateTime.now();
            BookInventory bookInventory = new BookInventory(1,book.getId(),numberAmount,time);
            HashMap<String,BookInventory> stringBookInventoryBHashMap = new HashMap<String, BookInventory>();
            stringBookInventoryBHashMap.put(book.getId(),bookInventory);
            book.setBookInventories(stringBookInventoryBHashMap);
        }
        return "order "+numberBook+" book susscess!";
    }
    @Benchmark
    public void order5Book() {
        System.out.println("Start order...");
        try {
            Thread.sleep(60000);
            orderBookInListBookAmount1(5,"");
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        System.out.println("End order");
    }
}
