package com.example.springBack.Application.Controller;
import com.example.springBack.Application.Repository.MessageRepository;
import com.example.springBack.Application.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
public class MessageController {

    @Autowired
    private MessageRepository MessageRepository;
    public MessageController() {
        // Конструктор по умолчанию
    }


    @GetMapping("/message")
    public Iterable<Message> getMessages(@RequestBody Message message) {
        return MessageRepository.findAll();
    }


    @GetMapping("/message/{id}")
    public Optional<Message> findMessageById(@PathVariable int id) {
        return MessageRepository.findById(id);
    }

    @PostMapping("/message")
    public ResponseEntity<Message> setPerson(@RequestBody Message message) {
        MessageRepository.save(message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping("/messagez")
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        return new ResponseEntity<Message>(message, HttpStatus.CREATED);
    }

    @PutMapping("/message/{id}")
    public ResponseEntity<Message> updatePerson(@PathVariable int id, @RequestBody Message message) {
        HttpStatus status = MessageRepository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity(MessageRepository.save(message), status);
    }

    @DeleteMapping("/message/{id}")
    public void deleteMessage(@PathVariable int id) {
        MessageRepository.deleteById(id);
    }
}
