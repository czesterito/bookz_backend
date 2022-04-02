package com.project.bookz.services.advertisement;

import com.project.bookz.models.Advertisement;
import com.project.bookz.repositories.AdvertisementRepository;
import com.project.bookz.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Advertisement> findBy(String text, String category, String city, Integer id, Integer pageNr) {
        Pageable page = PageRequest.of(pageNr, 10);
        return advertisementRepository.findAdvertisementByName(text, category, city, id, page);
    }

    @Override
    public List<Advertisement> findAllUserAdvertisements(Integer id) {
        return new ArrayList<>(advertisementRepository.findByBookUserUserId(id));
    }

    @Override
    public Advertisement changeAdvertisementDescription(Advertisement advertisement, String description) {
        advertisement.setDescription(description);
        return advertisementRepository.save(advertisement);
    }
}
