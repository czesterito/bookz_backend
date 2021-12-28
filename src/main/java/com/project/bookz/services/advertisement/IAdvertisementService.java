package com.project.bookz.services.advertisement;

import com.project.bookz.models.Advertisement;

import java.util.List;
import java.util.Optional;

public interface IAdvertisementService {

    List<Advertisement> findAllAdvertisements();

    Optional<Advertisement> findAdvertisementById(Integer id);

    void deleteAdvertisement(Integer id);

    Advertisement addNewAdvertisement(Advertisement newTeam);

    Advertisement updateAdvertisement(Advertisement oldAdvertisement, Advertisement newAdvertisement);

    List<Advertisement> findBy(String text, String category, String city, Integer id);
}
