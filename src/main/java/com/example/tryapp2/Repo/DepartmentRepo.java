package com.example.tryapp2.Repo;

import com.example.tryapp2.Entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Departments,Long> {
    Optional<Departments> findByDepartmentName(String departmentName);

}
