package com.project.bookz.repositories;

import com.project.bookz.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByUserUserId(Integer integer);

//    @Query("SELECT b FROM BOOK b WHERE LOWER(CONCAT(b.author, ' ', b.title)) LIKE CONCAT('%',:bookName,'%') " +
//            "OR LOWER(CONCAT(b.title, ' ', b.author)) LIKE CONCAT('%',:bookName,'%')")
//    List<Book> findBookByName(@Param("bookName") String bookName,
//                              @Param("category") String category, @Param("city") String city);
}
