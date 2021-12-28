package com.project.bookz.controllers;

import com.project.bookz.models.Advertisement;
import com.project.bookz.services.advertisement.IAdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "advertisement")
@RequiredArgsConstructor
public class AdvertisementController {

    private final IAdvertisementService advertisementService;

    @GetMapping("/")
    public ResponseEntity<?> getAllAdvertisements(){
        try{
            return new ResponseEntity<>(
                    advertisementService.findAllAdvertisements(),
                    HttpStatus.OK);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdvertisement(@PathVariable Integer id){
        try {
            Optional<Advertisement> optionalAdvertisement = advertisementService.findAdvertisementById(id);
            if(optionalAdvertisement.isPresent()){
                return new ResponseEntity<>(
                        optionalAdvertisement.get(),
                        HttpStatus.OK);
            }else return noAdvertisementFoundResponse(id);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createNewAdvertisement(@RequestBody Advertisement advertisement){
        try {
            return new ResponseEntity<>(
                    advertisementService.addNewAdvertisement(advertisement),
                    HttpStatus.CREATED);
        }catch (Exception e){
            return errorResponse();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdvertisement(@PathVariable Integer id){
        try {
            Optional<Advertisement> optionalAdvertisement = advertisementService.findAdvertisementById(id);
            if(optionalAdvertisement.isPresent()){
                advertisementService.deleteAdvertisement(id);
                return new ResponseEntity<>(
                        String.format("Meeting with id: %d was deleted", id),
                        HttpStatus.NO_CONTENT);
            }else return noAdvertisementFoundResponse(id);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdvertisement(@PathVariable Integer id, @RequestBody Advertisement advertisement){
        try {
            Optional<Advertisement> optionalAdvertisement = advertisementService.findAdvertisementById(id);
            if (optionalAdvertisement.isPresent()){
                return new ResponseEntity<>(
                        advertisementService.updateAdvertisement(optionalAdvertisement.get(), advertisement),
                        HttpStatus.OK);
            }else return noAdvertisementFoundResponse(id);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @GetMapping("/findBy")
    public ResponseEntity<?> searchForAdvertisement(@RequestParam String text, @RequestParam String category,
                                            @RequestParam String city, @RequestParam Integer id){
        try {
            return new ResponseEntity<>(advertisementService.findBy(text, category, city, id), HttpStatus.OK);
        } catch (Exception e){
            return errorResponse();
        }
    }








    private ResponseEntity<String> errorResponse(){
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> noAdvertisementFoundResponse(Integer id){
        return new ResponseEntity<>("Couldn't find advertisement with id: " + id, HttpStatus.NOT_FOUND);
    }
}
