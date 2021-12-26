package com.skillsack.ws.skills.vm;

import com.skillsack.ws.employees.Employees;
import com.skillsack.ws.skills.Skills;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SkillVM {

    private long id;

    private String name;

    private List<String> employees;

    public SkillVM(Skills name) {
        this.setId(name.getId());
        this.setName(name.getName());
        if (name.getEmployees() != null) {
            this.setEmployees(toEmployeeVMList(name.getEmployees()));
        }

    }

    public static List<String> toEmployeeVMList(List<Employees> employees) {
        List<String> employeesName = new ArrayList<>();
        for (Employees employee : employees) {
            employeesName.add(employee.getName());
        }
        return employeesName;
    }
}
