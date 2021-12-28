package com.skillsack.ws.departments;

import com.skillsack.ws.departments.vm.DepartmentSubmitVM;
import com.skillsack.ws.departments.vm.DepartmentVM;
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
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping("/department")
    GenericResponse saveDepartment(@Valid @RequestBody DepartmentSubmitVM departments) {
        departmentService.save(departments);
        return new GenericResponse("Department is saved");
    }

    @GetMapping("/departments")
    Page<DepartmentVM> getDepartments(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable page) {
        return departmentService.getDepartments(page).map(DepartmentVM::new);
    }

    @DeleteMapping("/department/{id}")
    GenericResponse deleteDepartment(@PathVariable("id") long id) {
        departmentService.deleteDepartment(id);
        return new GenericResponse("Department is deleted");
    }

    @PutMapping("/department/{id}")
    DepartmentVM updateDepartment(@PathVariable("id") long id, @Valid @RequestBody DepartmentSubmitVM updatedDepartment) {
        Departments department = departmentService.updateDepartment(id, updatedDepartment);
        return new DepartmentVM(department);
    }

    @GetMapping("/departments/{name}")
    Page<DepartmentVM> getDepartmentsByName(@PathVariable(value = "name") String name,
                                            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable page) {
        return departmentService.getDepartmentsByName(name, page).map(DepartmentVM::new);
    }


}
