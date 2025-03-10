package com.example.springBack.Application.Service;


import com.example.springBack.Application.Repository.PersonRepository;
import com.example.springBack.Application.dto.Message;
import com.example.springBack.Application.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    PersonRepository repository;
    @Autowired
    public PersonService(PersonRepository rep)
    {
        this.repository=rep;
    }

    public Optional<Message> addMeesageToPerson(int personId, Message message) {
        Person person = repository.findById(personId).get();
        Optional<Message> optional=Optional.ofNullable(null);
        if (repository.existsById(personId)) {
            message.setPerson(person);
            message.setBirthday(LocalDate.from(LocalDateTime.now()));
            person.addMessage(message);
            optional=Optional.of(message);
            repository.save(person);
            return optional;
        }

        return optional;
    }

    public Message deleteMessageByPersonId(int personId, Message message) {
        Person person = repository.findById(personId).get();
        if (repository.existsById(personId)) {
            var messagez=person.getMessages().stream().filter(x->x.getId()==message.getId()).findFirst();
            if(messagez.isPresent())
            {
                person.getMessages().remove(message);
            }
        }
        return message;
    }

    public List<Message> showUserMessages(int personId)
    {
        Person person = repository.findById(personId).get();
        if (repository.existsById(personId)) {
            var messagez=person.getMessages();
            if(!messagez.isEmpty())
            {
                return messagez;//HttpStatus.OK);
            }
        }
        //return new ResponseEntity<List<Message>>(new ArrayList<Message>(),HttpStatus.NO_CONTENT);
        return new ArrayList<>();

    }

    public Optional<Message> showUserMessageById_s(int personId, int messageId)
    {
        return showUserMessages(personId).stream().filter(message->message.getId()==messageId).findFirst();
    }

    public Person addPersonWithResponse(Person person) {
        repository.save(person);
        return  person;
    }

    public Person addPerson(Person person) {
        repository.save(person);
        return person;
    }


    public Iterable<Person> getPersons() {
        return repository.findAll();
    }

    public Optional<Person> findPersonById(@PathVariable int id) {
        return repository.findById(id);
    }

    public Optional<Person> updatePerson( int id, Person person) {
        var personz =findPersonById(id);
        if(personz.isPresent())
        {
            repository.save(person);
            return personz;
        }

        return Optional.of(null);
    }

    public void deletePerson(@PathVariable int id) {
        repository.deleteById(id);
    }
}
