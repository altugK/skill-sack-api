package com.skillsack.ws.employees;

import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employees, Long> {
    Employees findByName(String name);

}
