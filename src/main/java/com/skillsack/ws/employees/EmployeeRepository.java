package com.skillsack.ws.employees;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employees, Long> {

    Page<Employees> findByNameContains(String name, Pageable page);


}
