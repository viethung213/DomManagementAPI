package com.domhub.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.domhub.api.model.Message;

public interface MessageRepository  extends JpaRepository<Message, Integer> {
    List<Message> findBySentBy(@Param("sentById") Integer sentById);
}
