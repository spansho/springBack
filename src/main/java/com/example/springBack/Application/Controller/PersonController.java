package com.example.springBack.Application.Controller;
import com.example.springBack.Application.Repository.PersonRepository;
import com.example.springBack.Application.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository PersonRepository;
    public PersonController() {
        // Конструктор по умолчанию
    }

    @PostMapping("/person")
    public ResponseEntity<Person> setPerson(@RequestBody Person person) {
        PersonRepository.save(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @PostMapping("/persoN")
    public Person addPerson(@RequestBody Person person) {
        PersonRepository.save(person);
        return person;
    }

    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return PersonRepository.findAll();
    }


    @GetMapping("/person/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return PersonRepository.findById(id);
    }



    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        HttpStatus status = PersonRepository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity(PersonRepository.save(person), status);
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable int id) {
        PersonRepository.deleteById(id);
    }
}

