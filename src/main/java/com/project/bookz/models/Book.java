package com.project.bookz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "BOOK")
@Table(name = "`BOOK`")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", updatable = false)
    private Integer bookId;
    @Column(name = "title", nullable = false, columnDefinition = "TEXT")
    private String title;
    @Column(name = "author", nullable = false, columnDefinition = "TEXT")
    private String author;
    @Column(name = "category", nullable = false, columnDefinition = "TEXT")
    private String category;
    @Column(name = "taken")
    private Boolean taken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToOne(mappedBy = "book", cascade = {CascadeType.ALL, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JsonIgnore
    private Advertisement advertisement;

    public Book() {
    }

    public Book(String title, String author, String category, User user, Advertisement advertisement,
                Boolean taken) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.user = user;
        this.advertisement = advertisement;
        this.taken = taken;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public Boolean getTaken() {
        return taken;
    }

    public void setTaken(Boolean taken) {
        this.taken = taken;
    }
}
