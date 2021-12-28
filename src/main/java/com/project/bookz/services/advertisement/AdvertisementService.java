package com.project.bookz.services.advertisement;

import com.project.bookz.models.Advertisement;
import com.project.bookz.repositories.AdvertisementRepository;
import com.project.bookz.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertisementService implements IAdvertisementService{

    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;

    @Override
    public List<Advertisement> findAllAdvertisements() {
        return new ArrayList<>(advertisementRepository.findAll());
    }

    @Override
    public Optional<Advertisement> findAdvertisementById(Integer id) {
        return advertisementRepository.findById(id);
    }

    @Override
    public void deleteAdvertisement(Integer id) {
        advertisementRepository.deleteById(id);
    }

    @Override
    public Advertisement addNewAdvertisement(Advertisement newAdvertisement) {
        return advertisementRepository.save(newAdvertisement);
    }

    @Override
    public Advertisement updateAdvertisement(Advertisement oldAdvertisement, Advertisement newAdvertisement) {
        oldAdvertisement = newAdvertisement;
        return advertisementRepository.save(oldAdvertisement);
    }

    @Override
    public List<Advertisement> findBy(String text, String category, String city, Integer id) {
        List<Advertisement> advertisements = advertisementRepository.findAdvertisementByName(text, category, city);
        if(userRepository.findById(id).isPresent()) advertisements.removeAll(advertisementRepository.findByBook_User_UserId(id));
        return advertisements;
    }
}
