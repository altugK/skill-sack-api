package com.skillsack.ws.employees;

import com.skillsack.ws.employees.vm.EmployeeSubmitVM;
import com.skillsack.ws.employees.vm.EmployeeVM;
import com.skillsack.ws.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/1.0")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")
    GenericResponse saveEmployee(@Valid @RequestBody EmployeeSubmitVM employees) {
        employeeService.save(employees);
        return new GenericResponse("Employee is saved");
    }

    @GetMapping("/employees")
    Page<EmployeeVM> getEmployees(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 100) Pageable page) {
        return employeeService.getEmployees(page).map(EmployeeVM::new);
    }

    @DeleteMapping("/employee/{id}")
    GenericResponse deleteEmployee(@PathVariable(value = "id") long id) {
        employeeService.deleteEmployee(id);
        return new GenericResponse("Employee is deleted");
    }

    @PutMapping("/employee/{id}")
    EmployeeVM updateEmployee(@PathVariable(value = "id") long id, @RequestBody EmployeeSubmitVM updatedEmployee) {
        Employees employee = employeeService.updateEmployee(id, updatedEmployee);
        return new EmployeeVM(employee);
    }

}
