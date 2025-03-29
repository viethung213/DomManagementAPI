package com.domhub.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.domhub.api.model.MessageTo;
import com.domhub.api.model.MessageToId;

public interface MessageToRepository extends JpaRepository<MessageTo, MessageToId> {
    List<MessageTo> findById_Receiver(int receiver);
}
