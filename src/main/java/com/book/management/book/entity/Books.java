package com.book.management.book.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@NamedQuery(name = Books.FIND_BOOKS_BY_ID, query = "SELECT a FROM Books a WHERE a.id = :id")
@NamedQuery(name = Books.FIND_BOOKS_BY_AUTHOR, query = "SELECT a FROM Books a WHERE a.author = :author")
public class Books {

    public static final String FIND_BOOKS_BY_ID = "FindBooksById";
    public static final String FIND_BOOKS_BY_AUTHOR = "FindBooksByAuthor";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "date")
    private LocalDate date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String bookInfo(){
        return String.format(
                "Book ID: %d\nTitle: %s\nAuthor: %s\nDate: %s",
                getId(), getTitle(), getAuthor(), getDate()
        );
    }

    public String formatBookInfo() {
        return String.format(
                "Book ID: %d\nTitle: %s\nAuthor: %s\nDate: %s",
                getId(), getTitle(), getAuthor(), getDate()
        );
    }
}
