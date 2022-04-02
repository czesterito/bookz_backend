package com.project.bookz.controllers;

import com.project.bookz.models.Book;
import com.project.bookz.services.book.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RestController
@RequestMapping(path = "book")
@RequiredArgsConstructor
public class BookController {

    private final IBookService bookService;

    @RolesAllowed("user")
    @GetMapping("/")
    public ResponseEntity<?> getAllBooks(){
        try{
            return new ResponseEntity<>(
                    bookService.findAllBooks(),
                    HttpStatus.OK);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @RolesAllowed("user")
    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable Integer id){
        try {
            Optional<Book> optionalBook = bookService.findBookById(id);
            if(optionalBook.isPresent()){
                return new ResponseEntity<>(
                        optionalBook.get(),
                        HttpStatus.OK);
            }else return noBookFoundResponse(id);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @RolesAllowed("user")
    @PostMapping("/")
    public ResponseEntity<?> createNewBook(@RequestBody Book book){
        try {
            return new ResponseEntity<>(
                    bookService.addNewBook(book),
                    HttpStatus.CREATED);
        }catch (Exception e){
            return errorResponse();
        }
    }

    @RolesAllowed("user")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer id){
        try {
            Optional<Book> optionalBook = bookService.findBookById(id);
            if(optionalBook.isPresent()){
                bookService.deleteBook(id);
                return new ResponseEntity<>(
                        String.format("Meeting with id: %d was deleted", id),
                        HttpStatus.NO_CONTENT);
            }else return noBookFoundResponse(id);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @RolesAllowed("user")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Integer id, @RequestBody Book book){
        try {
            Optional<Book> optionalBook = bookService.findBookById(id);
            if (optionalBook.isPresent()){
                return new ResponseEntity<>(
                        bookService.updateBook(optionalBook.get(), book),
                        HttpStatus.OK);
            }else return noBookFoundResponse(id);

        }catch (Exception e){
            return errorResponse();
        }
    }








    private ResponseEntity<String> errorResponse(){
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> noBookFoundResponse(Integer id){
        return new ResponseEntity<>("Couldn't find book with id: " + id, HttpStatus.NOT_FOUND);
    }

}
