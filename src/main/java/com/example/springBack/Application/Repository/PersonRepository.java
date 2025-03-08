package com.example.springBack.Application.Repository;

import com.example.springBack.Application.dto.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PersonRepository extends CrudRepository<Person,Integer> {
}
