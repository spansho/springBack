package Controller;
import dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@RestController
public class MessageController {

    public MessageController() {
        // Конструктор по умолчанию
    }
    private List<Message> messageList = new ArrayList<>(Arrays.asList(
            new Message(1, "Работа", "Хотете найти работу?",  LocalDate.of(2010, 2,3)),
            new Message(2, "Врач", "Ваша запись перенесена на завтра",  LocalDate.of(2010, 2,2)),
            new Message(3, "Сообщение", "Как дела",  LocalDate.of(2010, 4,8)),
            new Message(4, "Уведомление", "Вам пришла зарплата",  LocalDate.of(2010, 6,5))
    ));

    @GetMapping("/message")
    public Iterable<Message> getMessages(@RequestBody Message message) {
        return messageList;
    }


    @GetMapping("/message/{id}")
    public Optional<Message> findMessageById(@PathVariable int id) {
        return messageList.stream().filter(p->p.getId()==id).findFirst();
    }

    @PostMapping("/message")
    public ResponseEntity<Message> setPerson(@RequestBody Message message) {
        messageList.add(message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping("/messagez")
    public Message addMessage(@RequestBody Message message) {
        messageList.add(message);
        return message;
    }

    @PutMapping("/message/{id}")
    public ResponseEntity<Message> updatePerson(@PathVariable int id, @RequestBody Message message) {
        int index = -1;
        for (Message p : messageList) {
            if (p.getId() == id) {
                index = messageList.indexOf(p);
                messageList.set(index, message);
            }
        }
        return index == -1
                ? new ResponseEntity<>(addMessage(message), HttpStatus.CREATED)
                : new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMessage(@PathVariable int id) {
        messageList.removeIf(p -> p.getId() == id);
    }
}
