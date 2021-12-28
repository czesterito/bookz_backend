package com.project.bookz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "USER")
@Table(name = "`USER`", uniqueConstraints = {
        @UniqueConstraint(name = "user_email_unique", columnNames = "email"),
        @UniqueConstraint(name = "user_username_unique", columnNames = "username")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Integer userId;
    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;
    @Column(name = "username", nullable = false, columnDefinition = "TEXT")
    private String username;
    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JsonIgnore
    private List<Book> books;

    public User() {
    }

    public User(String email, String username, String password, List<Book> books) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.books = books;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
