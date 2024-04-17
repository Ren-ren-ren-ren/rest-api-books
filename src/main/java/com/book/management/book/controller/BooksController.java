package com.book.management.book.controller;

import com.book.management.book.dto.BooksDto;
import com.book.management.book.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BooksController {

    @Autowired
    private BooksService booksService;
    @GetMapping("/find-book/via/id")
    public String getBookById(@RequestParam int id){

        return booksService.getBookById(id);
    }

    @GetMapping("/find-book/via/author")
    public String getBookByAuthor(@RequestParam String author){

        return booksService.getBookByAuthor(author);
    }

    @PostMapping("/book")
    public int createBook(@RequestBody BooksDto booksDto) {

        return booksService.createBook(booksDto);
    }

    @PutMapping("/book")
    public BooksDto updateBook(@RequestBody BooksDto booksDto) {

        return booksService.updateBook(booksDto);
    }

    @DeleteMapping("/book")
    public String deleteBook(@RequestParam int id) {

        return booksService.deleteBook(id);
    }

}
