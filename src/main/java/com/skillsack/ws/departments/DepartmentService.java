package com.skillsack.ws.departments;

import com.skillsack.ws.departments.vm.DepartmentSubmitVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DepartmentService {

    DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void save(DepartmentSubmitVM departments) {
        Departments department = new Departments();
        department.setName(departments.getName());
        department.setTimestamp(new Date());
        departmentRepository.save(department);
    }

    public Page<Departments> getDepartments(Pageable page) {
        return departmentRepository.findAll(page);
    }

    public void deleteDepartment(long id) {
        departmentRepository.deleteById(id);
    }

    public Departments updateDepartment(long id, DepartmentSubmitVM updatedDepartment) {
        Departments department = departmentRepository.findById(id).get();
        department.setName(updatedDepartment.getName());
        department.setTimestamp(new Date());
        return departmentRepository.save(department);
    }

    public Page<Departments> getDepartmentsByName(String name, Pageable page) {
        return departmentRepository.findByNameContains(name, page);
    }
}
