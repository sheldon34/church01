package com.example.tryapp2.Repo;

import com.example.tryapp2.Entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MediaRepo extends JpaRepository<Media,Long> {
}
