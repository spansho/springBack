package com.example.springBack.Application.Repository;

import com.example.springBack.Application.dto.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message,Integer> {
}
