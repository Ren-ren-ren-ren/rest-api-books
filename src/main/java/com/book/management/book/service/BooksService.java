package com.book.management.book.service;

import com.book.management.book.dao.BooksDao;
import com.book.management.book.dto.BooksDto;
import com.book.management.book.entity.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BooksService {

    @Autowired
    private BooksDao booksDao;

    public String getBookById(int id){

        Books books = booksDao.getBooksById(id);

        if(Objects.isNull(books)){
            return "No Books matches the give id: " + id;
        } else {
            return books.bookInfo();
        }
    }

    public String getBookByAuthor(String author){
        List <String> bookInfos = new ArrayList<>();
        List<Books> booksByAuthor = booksDao.getBooksByAuthor(author);

        if (Objects.isNull(booksByAuthor) || booksByAuthor.isEmpty()){
            bookInfos.add("No Books matches the give author: " + author);
        }
        for (Books books : booksByAuthor){
            bookInfos.add(books.bookInfo()+"\n");
        }
        return bookInfos.toString();
    }

    public int createBook(BooksDto booksDto) {

        return booksDao.createBook(mapBook(booksDto));
    }

    private Books mapBook(BooksDto booksDto) {

        Books books = new Books();
        books.setTitle(booksDto.getTitle());
        books.setAuthor(booksDto.getAuthor());
        books.setDate(booksDto.getDate());

        return books;
    }

    public BooksDto updateBook(BooksDto booksDto){
        Books books = booksDao.getBooksById(booksDto.getId());
            if (Objects.isNull(books)){
                return null;
            } else {
                books.setTitle(booksDto.getTitle());
                books.setAuthor(booksDto.getAuthor());
                books.setDate(booksDto.getDate());

                Books updatedBook = booksDao.updateBook(books);

                return mapBookDTO(updatedBook);
            }
    }

    private BooksDto mapBookDTO (Books books){

        BooksDto booksDto = new BooksDto();
        booksDto.setId(books.getId());
        booksDto.setTitle(books.getTitle());
        booksDto.setAuthor(books.getAuthor());
        booksDto.setDate(books.getDate());

        return booksDto;
    }

    public String deleteBook(int id) {

        boolean isBookDeleted = booksDao.deleteBook(id);

        if (isBookDeleted) {
            return "Book with id: " + id + " was successfully deleted.";
        } else {
            return "Book deletion failed.";
        }
    }
}
