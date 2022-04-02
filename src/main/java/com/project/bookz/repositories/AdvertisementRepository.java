package com.project.bookz.repositories;

import com.project.bookz.models.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer>, PagingAndSortingRepository<Advertisement, Integer> {
    List<Advertisement> findByBookUserUserId(Integer integer);

    @Query("SELECT a FROM ADVERTISEMENT a WHERE (LOWER(a.book.category) LIKE CONCAT('%',:category,'%') AND LOWER(a.city) LIKE CONCAT('%',:city,'%'))" +
            "AND (LOWER(CONCAT(a.book.author, ' ', a.book.title)) LIKE CONCAT('%',:bookName,'%') " +
            "OR LOWER(CONCAT(a.book.title, ' ', a.book.author)) LIKE CONCAT('%',:bookName,'%')) " +
            "AND a.book.user.userId <> :id")

    Page<Advertisement> findAdvertisementByName(@Param("bookName") String bookName,
                                                @Param("category") String category, @Param("city") String city,
                                                @Param("id") Integer id, Pageable pageable);
}
