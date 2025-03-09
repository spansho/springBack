package Service;


import com.example.springBack.Application.Repository.PersonRepository;
import com.example.springBack.Application.dto.Message;
import com.example.springBack.Application.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository repository;

    public ResponseEntity<Message> addMeesageToPerson(int personId, Message message) {
        Person person = repository.findById(personId).get();
        if (repository.existsById(personId)) {
            message.setPerson(person);
            message.setBirthday(LocalDate.from(LocalDateTime.now()));
            person.addMessage(message);
            return new ResponseEntity<Message>(message, HttpStatus.CREATED);
        }

        return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Message> deleteMessageByPersonId(int personId, Message message) {
        Person person = repository.findById(personId).get();
        if (repository.existsById(personId)) {
            var messagez=person.getMessages().stream().filter(x->x.getId()==message.getId()).findFirst();
            if(messagez.isPresent())
            {
                person.getMessages().remove(message);
                return new ResponseEntity<Message>(message,HttpStatus.OK);
            }
        }
        return new ResponseEntity<Message>(message,HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<Message>> showUserMessages(int personId)
    {
        Person person = repository.findById(personId).get();
        if (repository.existsById(personId)) {
            var messagez=person.getMessages();
            if(!messagez.isEmpty())
            {
                return new ResponseEntity<List<Message>>(messagez,HttpStatus.OK);
            }
        }
        return new ResponseEntity<List<Message>>(new ArrayList<Message>(),HttpStatus.NO_CONTENT);

    }
}
