package com.project.bookz.controllers;

import com.project.bookz.dto.MessageDto;
import com.project.bookz.models.Message;
import com.project.bookz.models.Message;
import com.project.bookz.services.message.IMessageService;
import com.project.bookz.services.message.MessageService;
import com.project.bookz.services.message.IMessageService;
import com.project.bookz.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Optional;

@RestController
@RequestMapping(path = "message")
@RequiredArgsConstructor
public class MessageController {

    private final IMessageService messageService;

    @RolesAllowed("user")
    @GetMapping("/")
    public ResponseEntity<?> getAllMessages(){
        try{
            return new ResponseEntity<>(
                    messageService.findAllMessages(),
                    HttpStatus.OK);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @RolesAllowed("user")
    @GetMapping("/{id}")
    public ResponseEntity<?> getMessage(@PathVariable Integer id){
        try {
            Optional<Message> optionalMessage = messageService.findMessageById(id);
            if(optionalMessage.isPresent()){
                return new ResponseEntity<>(
                        optionalMessage.get(),
                        HttpStatus.OK);
            }else return noMessageFoundResponse(id);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @RolesAllowed("user")
    @PostMapping("/")
    public ResponseEntity<?> createNewMessage(@RequestBody MessageDto message){
        try {
            return new ResponseEntity<>(
                    messageService.addNewMessage(message),
                    HttpStatus.CREATED);
        }catch (Exception e){
            return errorResponse();
        }
    }

    @RolesAllowed("user")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Integer id){
        try {
            Optional<Message> optionalMessage = messageService.findMessageById(id);
            if(optionalMessage.isPresent()){
                messageService.deleteMessage(id);
                return new ResponseEntity<>(
                        String.format("Message with id: %d was deleted", id),
                        HttpStatus.NO_CONTENT);
            }else return noMessageFoundResponse(id);

        }catch (Exception e){
            return errorResponse();
        }
    }

    @RolesAllowed("user")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMessage(@PathVariable Integer id, @RequestBody Message message){
        try {
            Optional<Message> optionalMessage = messageService.findMessageById(id);
            if (optionalMessage.isPresent()){
                return new ResponseEntity<>(
                        messageService.updateMessage(optionalMessage.get(), message),
                        HttpStatus.OK);
            }else return noMessageFoundResponse(id);

        }catch (Exception e){
            return errorResponse();
        }
    }








    private ResponseEntity<String> errorResponse(){
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> noMessageFoundResponse(Integer id){
        return new ResponseEntity<>("Couldn't find message with id: " + id, HttpStatus.NOT_FOUND);
    }

}
