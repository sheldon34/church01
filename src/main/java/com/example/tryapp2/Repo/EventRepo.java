package com.example.tryapp2.Repo;

import com.example.tryapp2.Entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends JpaRepository<Events, Long> {
    Events getByEventName(String eventName);
}
