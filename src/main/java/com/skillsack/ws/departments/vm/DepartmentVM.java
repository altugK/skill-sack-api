package com.skillsack.ws.departments.vm;

import com.skillsack.ws.departments.Departments;
import com.skillsack.ws.employees.Employees;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class DepartmentVM {

    private long id;

    private String name;

    private List<String> employees;

    public DepartmentVM(Departments department) {
        this.setId(department.getId());
        this.setName(department.getName());
        this.setEmployees(toEmployeeVMList(department.getEmployees()));
    }

    public static List<String> toEmployeeVMList(List<Employees> employees) {
        List<String> employeesName = new ArrayList<>();
        for (Employees employee : employees) {
            employeesName.add(employee.getName());
        }
        return employeesName;
    }
}
