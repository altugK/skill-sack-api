package com.skillsack.ws.employees;

import com.skillsack.ws.departments.DepartmentRepository;
import com.skillsack.ws.departments.Departments;
import com.skillsack.ws.employees.vm.EmployeeSubmitVM;
import com.skillsack.ws.error.NotFoundException;
import com.skillsack.ws.skills.SkillRepository;
import com.skillsack.ws.skills.Skills;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    DepartmentRepository departmentRepository;

    SkillRepository skillsRepository;

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, SkillRepository skillsRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.skillsRepository = skillsRepository;
    }

    public void save(EmployeeSubmitVM savedEmployee) {
        Employees employee = new Employees();
        checkAndUpdate(savedEmployee, employee);
        employeeRepository.save(employee);
    }

    public Page<Employees> getEmployees(Pageable page) {
        return employeeRepository.findAll(page);
    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    public Employees updateEmployee(long id, EmployeeSubmitVM updatedEmployee) {

        Employees employee = employeeRepository.findById(id).get();
        if (employee == null) {
            throw new NotFoundException();
        }
        if (updatedEmployee.getName() == null) {
            updatedEmployee.setName(employee.getName());
        }

        checkAndUpdate(updatedEmployee, employee);

        return employeeRepository.save(employee);
    }

    private void checkAndUpdate(EmployeeSubmitVM updatedEmployee, Employees oldEmployee) {
        oldEmployee.setName(updatedEmployee.getName());
        oldEmployee.setTimestamp(new Date());

        if (updatedEmployee.getDepartment() != null) {
            Optional<Departments> department = departmentRepository.findById(updatedEmployee.getDepartment());
            if (department.isPresent()) {
                oldEmployee.setDepartment(department.get());
            }
        }

        if (updatedEmployee.getSkill() != null) {
            Optional<Skills> updatedSkill = skillsRepository.findById(updatedEmployee.getSkill());

            if (updatedSkill.isPresent()) {
                List<Skills> newSkills = new ArrayList<>();
                if (oldEmployee.getSkills() != null) {
                    newSkills.addAll(oldEmployee.getSkills());
                }
                if (newSkills.contains(updatedSkill.get())) {
                    throw new NotFoundException();
                }
                newSkills.add(updatedSkill.get());
                oldEmployee.setSkills(newSkills);
            }
        }
    }

    public Page<Employees> getEmployeesByName(String name, Pageable page) {
        return employeeRepository.findByNameContains(name, page);
    }

    public void deleteEmployeeSkill(long employeeId, long skillId) {
        Optional<Employees> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            List<Skills> skills = employee.get().getSkills();
            if (skills != null) {
                skills.removeIf(skill -> skill.getId() == skillId);
                employee.get().setSkills(skills);
            }
        }
        employeeRepository.save(employee.get());
    }
}
