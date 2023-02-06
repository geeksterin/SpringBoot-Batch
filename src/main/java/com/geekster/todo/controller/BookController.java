package com.geekster.todo.controller;

import com.geekster.todo.model.Book;
import jakarta.annotation.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping("/book")
    public Book getBook(@Nullable @RequestParam("id") String id,
                        @Nullable @RequestParam("size") String size,
                        @Nullable @RequestParam("availability") String availability) {

        Book book = new Book();
        if (null != id) {
            book.setBookId(Integer.parseInt(id));
        }else {
            book.setBookId(1);
        }
        book.setBookAuthor("John");
        book.setBookName("Tiger");
        return book;
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable String id) {

        Book book = new Book();
        book.setBookId(Integer.valueOf(id));
        book.setBookAuthor("John-2");
        book.setBookName("Tiger-2");
        return book;
    }

    @PostMapping("/book")
    public String saveBook(@RequestBody Book book) {

        System.out.println("id- " + book.getBookId());
        System.out.println("name- " + book.getBookName());
        System.out.println("Author- " + book.getBookAuthor());

        return "saved";
    }


}
