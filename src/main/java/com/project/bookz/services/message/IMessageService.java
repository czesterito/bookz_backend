package com.project.bookz.services.message;

import com.project.bookz.dto.MessageDto;
import com.project.bookz.models.Message;

import java.util.List;
import java.util.Optional;

public interface IMessageService {
    List<Message> findAllMessages();

    Optional<Message> findMessageById(Integer id);

    void deleteMessage(Integer id);

    Message addNewMessage(MessageDto newMessage);

    Message updateMessage(Message oldMessage, Message newMessage);
}
