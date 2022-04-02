package com.project.bookz.services.message;

import com.project.bookz.dto.MessageDto;
import com.project.bookz.models.Message;
import com.project.bookz.models.Offer;
import com.project.bookz.repositories.MessageRepository;
import com.project.bookz.repositories.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService{
    private final MessageRepository messageRepository;
    private final OfferRepository offerRepository;

    @Override
    public List<Message> findAllMessages() {
        return new ArrayList<>(messageRepository.findAll());
    }

    @Override
    public Optional<Message> findMessageById(Integer id) {
        return messageRepository.findById(id);
    }

    @Override
    public void deleteMessage(Integer id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Message addNewMessage(MessageDto newMessageDto) {
        Offer offer = offerRepository.getById(newMessageDto.getOfferId());
        Message newMessage = new Message(newMessageDto.getContent(), newMessageDto.getTime(), newMessageDto.getNameUser(),offer );
        return messageRepository.save(newMessage);
    }

    @Override
    public Message updateMessage(Message oldMessage, Message newMessage) {
        oldMessage = newMessage;
        return messageRepository.save(oldMessage);
    }
}
