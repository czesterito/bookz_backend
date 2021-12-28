package com.project.bookz.services.book;

import com.project.bookz.models.Book;
import com.project.bookz.repositories.BookRepository;
import com.project.bookz.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService{

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public List<Book> findAllBooks() {
        return new ArrayList<>(bookRepository.findAll());
    }

    @Override
    public Optional<Book> findBookById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book addNewBook(Book newBook) {
        return bookRepository.save(newBook);
    }

    @Override
    public Book updateBook(Book oldBook, Book newBook) {
        oldBook = newBook;
        return bookRepository.save(oldBook);
    }

//    @Override
//    public List<Book> findBy(String text, String category, String city, Integer id) {
//        List<Book> books = bookRepository.findBookByName(text, category, city);
//        if(userRepository.findById(id).isPresent()) books.removeAll(bookRepository.findByUser_UserId(id));
//        return books;
//    }
}
