package com.provsky.university_interface.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentsRepo extends CrudRepository<Department, Integer> {
    Department findByName(String name);
}
