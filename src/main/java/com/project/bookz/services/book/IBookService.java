package com.project.bookz.services.book;

import com.project.bookz.models.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {

    List<Book> findAllBooks();

    Optional<Book> findBookById(Integer id);

    void deleteBook(Integer id);

    Book addNewBook(Book newTeam);

    Book updateBook(Book oldBook, Book newBook);

//    List<Book> findBy(String text, String category, String city, Integer id);
}
