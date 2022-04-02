package com.project.bookz.services.advertisement;

import com.project.bookz.models.Advertisement;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IAdvertisementService {

    List<Advertisement> findAllAdvertisements();

    Optional<Advertisement> findAdvertisementById(Integer id);

    void deleteAdvertisement(Integer id);

    Advertisement addNewAdvertisement(Advertisement newAdvertisement);

    Advertisement updateAdvertisement(Advertisement oldAdvertisement, Advertisement newAdvertisement);

    Page<Advertisement> findBy(String text, String category, String city, Integer id, Integer pageNr);

    List<Advertisement> findAllUserAdvertisements(Integer id);

    Advertisement changeAdvertisementDescription(Advertisement advertisement, String description);
}
