package com.project.bookz.services.offer;

import com.project.bookz.models.Offer;

import java.util.List;
import java.util.Optional;

public interface IOfferService {
    List<Offer> findAllOffers();

    Optional<Offer> findOfferById(Integer id);

    void deleteOffer(Integer id);

    Offer addNewOffer(Offer newTeam);

    Offer updateOffer(Offer oldOffer, Offer newOffer);

    List<Offer> findAllUserOffers(Integer id);
}
