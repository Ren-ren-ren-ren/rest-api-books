package com.book.management.book.dao;

import com.book.management.book.entity.Books;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class BooksDao {

    @Autowired
    private EntityManager entityManger;
    public Books getBooksById(int id){

        return entityManger.createNamedQuery(Books.FIND_BOOKS_BY_ID, Books.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public List<Books> getBooksByAuthor(String author){

        return entityManger.createNamedQuery(Books.FIND_BOOKS_BY_AUTHOR, Books.class)
                .setParameter("author", author)
                .getResultList();
    }

    @Transactional
    public int createBook(Books books){

        entityManger.persist(books);
        return books.getId();
    }

    @Transactional
    public Books updateBook(Books books){

        return entityManger.merge(books);
    }

    @Transactional
    public boolean deleteBook(int id) {

        Books books = entityManger.createNamedQuery(Books.FIND_BOOKS_BY_ID, Books.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);

        if (Objects.isNull(books)) {
            return false;
        } else {
            entityManger.remove(books);

            return true;
        }
    }
}
