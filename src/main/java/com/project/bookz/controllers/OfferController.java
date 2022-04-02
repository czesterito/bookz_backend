package com.project.bookz.controllers;

import com.project.bookz.models.Offer;
import com.project.bookz.services.offer.IOfferService;
import com.project.bookz.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RestController
@RequestMapping(path = "offer")
@RequiredArgsConstructor
public class OfferController {

    private final IOfferService offerService;
    private final IUserService userService;

    @RolesAllowed("user")
    @GetMapping("/")
    public ResponseEntity<?> getAllOffers(){
        try{
            return new ResponseEntity<>(
                    offerService.findAllOffers(),
                    HttpStatus.OK);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @RolesAllowed("user")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOffer(@PathVariable Integer id){
        try {
            Optional<Offer> optionalOffer = offerService.findOfferById(id);
            if(optionalOffer.isPresent()){
                return new ResponseEntity<>(
                        optionalOffer.get(),
                        HttpStatus.OK);
            }else return noOfferFoundResponse(id);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @RolesAllowed("user")
    @PostMapping("/")
    public ResponseEntity<?> createNewOffer(@RequestBody Offer offer){
        try {
            return new ResponseEntity<>(
                    offerService.addNewOffer(offer),
                    HttpStatus.CREATED);
        }catch (Exception e){
            return errorResponse();
        }
    }

    @RolesAllowed("user")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable Integer id){
        try {
            Optional<Offer> optionalOffer = offerService.findOfferById(id);
            if(optionalOffer.isPresent()){
                offerService.deleteOffer(id);
                return new ResponseEntity<>(
                        String.format("Meeting with id: %d was deleted", id),
                        HttpStatus.NO_CONTENT);
            }else return noOfferFoundResponse(id);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @RolesAllowed("user")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOffer(@PathVariable Integer id, @RequestBody Offer offer){
        try {
            Optional<Offer> optionalOffer = offerService.findOfferById(id);
            if (optionalOffer.isPresent()){
                return new ResponseEntity<>(
                        offerService.updateOffer(optionalOffer.get(), offer),
                        HttpStatus.OK);
            }else return noOfferFoundResponse(id);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @RolesAllowed("user")
    @GetMapping("/user/{user_id}")
    public ResponseEntity<?>getUserOffers(@PathVariable Integer user_id) {
        try {
            if (userService.findUserById(user_id).isPresent()) return new ResponseEntity<>(offerService.findAllUserOffers(user_id), HttpStatus.OK);
            return noUserFoundResponse(user_id);
        } catch (Exception e){
            return errorResponse();
        }
    }

    private ResponseEntity<String> errorResponse(){
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> noOfferFoundResponse(Integer id){
        return new ResponseEntity<>("Couldn't find offer with id: " + id, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<String> noUserFoundResponse(Integer id){
        return new ResponseEntity<>("Couldn't find user with id: " + id, HttpStatus.NOT_FOUND);
    }
}
