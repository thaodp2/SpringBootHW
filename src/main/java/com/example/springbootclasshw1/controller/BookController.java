package com.example.springbootclasshw1.controller;


import com.example.springbootclasshw1.event.BookEvent;
import com.example.springbootclasshw1.model.Book;
import com.example.springbootclasshw1.model.BookInventory;
import com.example.springbootclasshw1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/book/test")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookService.getAllListBooks();
    }

    @PostMapping("/booksInventory")
    public HashMap<String, BookInventory> getAllBooksInventory(@RequestBody String idBook) {
        return bookService.getAllListBooksInventory(idBook);
    }
    @GetMapping("/booksSortByTitle")
    public List<Book> getAllBooksByTitle() {
        return bookService.getAllListBooksSortByTitle();
    }

    @PostMapping("/searchBookByTitle")
    public List<Book> searchBookByTitle(@RequestBody String title ) {
        return bookService.getAllListBookSearchByTitle(title);
    }

    @GetMapping("/searchBookByAmount")
    public List<Book> searchBookByTitle() {
        return bookService.getAllListBookHaveAmountZero();
    }

    @PostMapping("/buyBook")
    public String buyBook(@RequestBody int numberBook) {
        return bookService.buyBook("3",numberBook);
    }

    @GetMapping("/order")
    public String orderBook() {
        return bookService.orderBook("3",3);
    }

    @GetMapping("/orderListBookAmountOnly1")
    public String orderListBookAmountOnly1() {
        return bookService.orderBookInListBookAmount1(5,"");
    }

    @PostMapping("/orderListBook")
    public String orderListBook(@RequestBody int numberBookOrder,String idBook) {
        return bookService.orderBookInListBookAmount1(numberBookOrder, idBook);
    }


    @PostMapping("/orderBook")
    @ResponseBody
    public String customerMakeOrder(@RequestBody Order order) {
        System.out.println("Customer rent a film");
        BookEvent customEvent = new BookEvent(order, "ORDER");
        applicationEventPublisher.publishEvent(customEvent);
        return "Order is being proccesed";
    }
}
