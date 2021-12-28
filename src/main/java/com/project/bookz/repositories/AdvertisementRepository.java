package com.project.bookz.repositories;

import com.project.bookz.models.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {
    List<Advertisement> findByBook_User_UserId(Integer integer);

    @Query("SELECT a FROM ADVERTISEMENT a WHERE (LOWER(a.book.category) LIKE CONCAT('%',:category,'%') AND LOWER(a.city) LIKE CONCAT('%',:city,'%'))" +
            "AND (LOWER(CONCAT(a.book.author, ' ', a.book.title)) LIKE CONCAT('%',:bookName,'%') " +
            "OR LOWER(a.book.category) LIKE CONCAT('%',:category,'%') AND LOWER(a.city) LIKE CONCAT('%',:city,'%'))")

//    @Query("SELECT a FROM ADVERTISEMENT a WHERE LOWER(CONCAT(a.book.author, ' ', a.book.title)) LIKE CONCAT('%',:bookName,'%') " +
//            "OR LOWER(CONCAT(a.book.title, ' ', a.book.author)) LIKE CONCAT('%',:bookName,'%') " +
//            "AND LOWER(a.book.category) LIKE CONCAT('%',:category,'%') AND LOWER(a.city) LIKE CONCAT('%',:city,'%')")
    List<Advertisement> findAdvertisementByName(@Param("bookName") String bookName,
                              @Param("category") String category, @Param("city") String city);
}
