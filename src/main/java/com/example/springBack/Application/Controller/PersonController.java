package com.example.springBack.Application.Controller;
import com.example.springBack.Application.Repository.PersonRepository;
import com.example.springBack.Application.Service.PersonService;
import com.example.springBack.Application.dto.Message;
import com.example.springBack.Application.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonService service;
    public PersonController(PersonService service) {
       this.service=service;
    }

    @PostMapping("/person")
    public ResponseEntity<Person> setPerson(@RequestBody Person person) {
        return new ResponseEntity<>( service.addPersonWithResponse(person),HttpStatus.OK);
    }

    @PostMapping("/person/{id}/message")
    public ResponseEntity<Optional<Message>> addMessage(@PathVariable int id, @RequestBody Message message) {
        var messagez = service.addMeesageToPerson(id,message);
        if(messagez.isPresent()) return new ResponseEntity<>(messagez,HttpStatus.OK);
        return new ResponseEntity<>(messagez,HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/persoN")
    public Person addPerson(@RequestBody Person person) {
       return service.addPerson(person);
    }

    @GetMapping("/persons")
    public ResponseEntity<Iterable<Person>> getPersons() {
        if(service.getPersons().iterator().hasNext()) return new ResponseEntity<>( service.getPersons(),HttpStatus.OK);

        return new ResponseEntity<>( service.getPersons(),HttpStatus.NOT_FOUND);
    }

    @GetMapping("/person/{id}/message")
    public ResponseEntity<Iterable<Message>> getPersonMessages(@PathVariable int id)
    {
          var messages= service.showUserMessages(id);
          if(!messages.isEmpty()) return new ResponseEntity<Iterable<Message>>(service.showUserMessages(id),HttpStatus.OK);
          return new ResponseEntity<Iterable<Message>>(service.showUserMessages(id),HttpStatus.NOT_FOUND);
    }


    @GetMapping("person/{idPers}/message/{idMess}")
    public ResponseEntity<Optional<Message>> getPesonIdMessageId(@PathVariable int idPers,@PathVariable int idMess)
    {
        var message=service.showUserMessageById_s(idPers,idMess);
        if(message.isPresent()) return new ResponseEntity<>(message,HttpStatus.OK);

        return new ResponseEntity<>(message,HttpStatus.NO_CONTENT);
    }


    @GetMapping("/person/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return service.findPersonById(id);
    }






    @PutMapping("/person/{id}")
    public ResponseEntity<Optional<Person>> updatePerson(@PathVariable int id, @RequestBody Person person) {
        var personz=service.updatePerson(id,person);
        if(personz.isPresent()) return new ResponseEntity<>(service.updatePerson(id,person),HttpStatus.OK);
        return new ResponseEntity<>(service.updatePerson(id,person),HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable int id) {
       service.deletePerson(id);
    }
}

