package com.project.bookz.services.offer;

import com.project.bookz.models.Offer;
import com.project.bookz.repositories.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfferService implements IOfferService {

    private final OfferRepository offerRepository;

    @Override
    public List<Offer> findAllOffers() {
        return new ArrayList<>(offerRepository.findAll());
    }

    @Override
    public Optional<Offer> findOfferById(Integer id) {
        return offerRepository.findById(id);
    }

    @Override
    public void deleteOffer(Integer id) {
        offerRepository.deleteById(id);
    }

    @Override
    public Offer addNewOffer(Offer newOffer) {
        return offerRepository.save(newOffer);
    }

    @Override
    public Offer updateOffer(Offer oldOffer, Offer newOffer) {
        oldOffer = newOffer;
        return offerRepository.save(oldOffer);
    }

    @Override
    public List<Offer> findAllUserOffers(Integer id) {
        List<Offer> offers = offerRepository.findByBookUserUserId(id);
        offers.addAll(offerRepository.findByAdvertisementBookUserUserId(id));
        return offers;
    }
}
