package com.codegym.backend.repository;

import com.codegym.backend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Integer> {
    @Query(value = "SELECT * FROM message", nativeQuery = true)
    List<Message> findMessage();
    @Query(value = "SELECT * FROM message WHERE id = ?", nativeQuery = true)
    Message findMessageById(int id);
}
