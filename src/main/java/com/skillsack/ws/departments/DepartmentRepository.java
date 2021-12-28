package com.skillsack.ws.departments;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Departments, Long> {
    Page<Departments> findByNameContains(String name, Pageable page);
}
