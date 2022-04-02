package com.project.bookz.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "OFFER")
@Table(name = "`OFFER`")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id", updatable = false)
    private Integer offerId;

    @Column(name = "accepted")
    private Boolean accepted;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offered_book_id", referencedColumnName = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id", referencedColumnName = "advertisement_id")
    private Advertisement advertisement;

    @OneToMany(mappedBy = "offer", cascade = {CascadeType.ALL, CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Message> messages;


    public Offer() {
    }

    public Offer(Book book, Advertisement advertisement, List<Message> messages, Boolean accepted) {
        this.book = book;
        this.advertisement = advertisement;
        this.messages = messages;
        this.accepted = accepted;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
