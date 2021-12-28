package com.project.bookz.models;

import javax.persistence.*;

@Entity(name = "ADVERTISEMENT")
@Table(name = "`ADVERTISEMENT`")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advertisement_id", updatable = false)
    private Integer advertisementId;
    @Column(name = "city", nullable = false, columnDefinition = "TEXT")
    private String city;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "active")
    private Boolean active;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    public Advertisement() {
    }

    public Advertisement(String city, String description, Boolean active, Book book) {
        this.city = city;
        this.description = description;
        this.active = active;
        this.book = book;
    }

    public Integer getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(Integer advertisementId) {
        this.advertisementId = advertisementId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
