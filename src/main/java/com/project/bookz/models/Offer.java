package com.project.bookz.models;

import javax.persistence.*;

@Entity(name = "OFFER")
@Table(name = "`OFFER`")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offer_id", updatable = false)
    private Integer userId;

    public Offer() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
