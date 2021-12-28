package com.project.bookz.repositories;

import com.project.bookz.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM USER u WHERE LOWER(u.username) LIKE CONCAT('%',:username,'%')")
    List<User> findUsersWithPartOfName(@Param("username") String username);

}
