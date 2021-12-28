package com.project.bookz.controllers;

import com.project.bookz.models.User;
import com.project.bookz.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers(){
        try{
            return new ResponseEntity<>(
                    userService.findAllUsers(),
                    HttpStatus.OK);
        }catch (Exception e){
            return errorResponse();
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getUser(@PathVariable Integer user_id){
        try {
            Optional<User> optionalUser = userService.findUserById(user_id);
            if(optionalUser.isPresent()){
                return new ResponseEntity<>(
                        optionalUser.get(),
                        HttpStatus.OK);
            }else return noUserFoundResponse(user_id);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @GetMapping("/email")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email){
        try {
            Optional<User> optionalUser = userService.findUserByEmail(email);
            if (optionalUser.isPresent()){
                return new ResponseEntity<>(
                        optionalUser.get(),
                        HttpStatus.OK);
            }else return noUserFoundResponse(email);
        }catch (Exception e){
            return errorResponse();
        }
    }

    @GetMapping("/username")
    public ResponseEntity<?> getUserByName(@RequestParam String username){
        try {
            Optional<User> optionalUser = userService.findUserByName(username);
            if (optionalUser.isPresent()){
                return new ResponseEntity<>(
                        optionalUser.get(),
                        HttpStatus.OK);
            }else return noUserFoundResponse(username);
        }catch (Exception e){
            return errorResponse();
        }
    }

    @GetMapping("/{user_id}/books")
    public ResponseEntity<?>getUserBooks(@PathVariable Integer user_id) {
        try {
            if (userService.findUserById(user_id).isPresent()) return new ResponseEntity<>(userService.findUserById(user_id).get().getBooks(), HttpStatus.OK);
            return noUserFoundResponse(user_id);
        } catch (Exception e){
            return errorResponse();
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createNewUser(@RequestBody User user){
        try {
            if (userService.checkIfEmailIsTaken(user.getEmail())) return emailTakenResponse();

            else {
                return new ResponseEntity<>(
                        userService.addNewUser(user),
                        HttpStatus.CREATED);
            }
        }catch (Exception e){
            return errorResponse();
        }
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer user_id){
        try {
            Optional<User> optionalUser = userService.findUserById(user_id);
            if(optionalUser.isPresent()){
                userService.deleteUser(user_id);
                return new ResponseEntity<>(
                        String.format("User with id: %d was deleted", user_id),
                        HttpStatus.OK);
            }else return noUserFoundResponse(user_id);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer user_id, @RequestBody User user){
        try {
            Optional<User> optionalUser = userService.findUserById(user_id);
            if (optionalUser.isPresent()){
                return new ResponseEntity<>(
                        userService.updateUser(optionalUser.get(), user),
                        HttpStatus.OK);
            }else return noUserFoundResponse(user_id);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @GetMapping("/emailTaken")
    public ResponseEntity<?>checkIfEmailIsTaken(@RequestParam String email){
        try{
            if (userService.checkIfEmailIsTaken(email)) return new ResponseEntity<> (email, HttpStatus.OK);
            else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @GetMapping("/nameTaken")
    public ResponseEntity<?>checkIfNameIsTaken(@RequestParam String username){
        try{
            if (userService.checkIfNameIsTaken(username)) return new ResponseEntity<> (username, HttpStatus.OK);
            else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

        }catch (Exception e){
            return errorResponse();
        }
    }





    private ResponseEntity<String> errorResponse(){
        return new ResponseEntity<>("Something went wrong ", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ResponseEntity<String> emailTakenResponse(){
        return new ResponseEntity<>("Email is already taken ", HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> nameTakenResponse(){
        return new ResponseEntity<>("Name is already taken ", HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> noUserFoundResponse(Integer id){
        return new ResponseEntity<>("Couldn't find user with id: " + id, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<String> noUserFoundResponse(String email){
        return new ResponseEntity<>("Couldn't find user with email: " + email, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<String> wrongCredentials(){
        return new ResponseEntity<>("Wrong credentials", HttpStatus.NOT_FOUND);
    }
}
