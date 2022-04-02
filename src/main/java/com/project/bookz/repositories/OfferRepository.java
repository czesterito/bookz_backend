package com.project.bookz.repositories;

import com.project.bookz.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    List<Offer> findByBookUserUserId(Integer integer);

    List<Offer> findByAdvertisementBookUserUserId(Integer integer);
}
