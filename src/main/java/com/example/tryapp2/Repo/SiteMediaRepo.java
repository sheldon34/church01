package com.example.tryapp2.Repo;

import com.example.tryapp2.Entity.siteMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteMediaRepo extends JpaRepository<siteMedia, Long > {
}
