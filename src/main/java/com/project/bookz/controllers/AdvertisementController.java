package com.project.bookz.controllers;

import com.project.bookz.models.Advertisement;
import com.project.bookz.services.advertisement.IAdvertisementService;
import com.project.bookz.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RestController
@RequestMapping(path = "advertisement")
@RequiredArgsConstructor
public class AdvertisementController {

    private final IAdvertisementService advertisementService;
    private final IUserService userService;

    @RolesAllowed("user")
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

    @RolesAllowed("user")
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

    @RolesAllowed("user")
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

    @RolesAllowed("user")
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

    @RolesAllowed("user")
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

    @RolesAllowed("user")
    @GetMapping("/findBy")
    public ResponseEntity<?> searchForAdvertisement(@RequestParam String text, @RequestParam String category,
                                            @RequestParam String city, @RequestParam Integer id, @RequestParam Integer pageNr){
        try {
            return new ResponseEntity<>(advertisementService.findBy(text, category, city, id, pageNr), HttpStatus.OK);
        } catch (Exception e){
            return errorResponse();
        }
    }

    @RolesAllowed("user")
    @GetMapping("/user/{user_id}")
    public ResponseEntity<?>getUserAdvertisements(@PathVariable Integer user_id) {
        try {
            if (userService.findUserById(user_id).isPresent()) return new ResponseEntity<>(advertisementService.findAllUserAdvertisements(user_id), HttpStatus.OK);
            return noUserFoundResponse(user_id);
        } catch (Exception e){
            return errorResponse();
        }
    }

    @RolesAllowed("user")
    @PutMapping("/{advertisement_id}/changeDescription")
    public ResponseEntity<?> changeDescription(@PathVariable Integer advertisement_id, @RequestParam String newDescription){
        try {
            Optional<Advertisement> optionalAdvertisement = advertisementService.findAdvertisementById(advertisement_id);
            if(optionalAdvertisement.isPresent()){
                return new ResponseEntity<>(
                        advertisementService.changeAdvertisementDescription(optionalAdvertisement.get(), newDescription),
                        HttpStatus.NO_CONTENT);}
            return noAdvertisementFoundResponse(advertisement_id);
        }catch (Exception e){
            return errorResponse();
        }
    }








    private ResponseEntity<String> errorResponse(){
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> noAdvertisementFoundResponse(Integer id){
        return new ResponseEntity<>("Couldn't find advertisement with id: " + id, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<String> noUserFoundResponse(Integer id){
        return new ResponseEntity<>("Couldn't find user with id: " + id, HttpStatus.NOT_FOUND);
    }
}
