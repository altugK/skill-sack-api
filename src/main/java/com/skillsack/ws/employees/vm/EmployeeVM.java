package com.skillsack.ws.employees.vm;

import com.skillsack.ws.departments.vm.DepartmentVM;
import com.skillsack.ws.employees.Employees;
import com.skillsack.ws.skills.Skills;
import com.skillsack.ws.skills.vm.SkillVM;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class EmployeeVM {

    private long id;

    private String name;

    private DepartmentVM department;

    private List<SkillVM> skills;

    public EmployeeVM(Employees name) {
        this.setId(name.getId());
        this.setName(name.getName());
        if (name.getDepartment() != null) {
            this.setDepartment(new DepartmentVM(name.getDepartment()));
        }
        if (name.getSkills() != null) {
            this.setSkills(toSkillVMList(name.getSkills()));
        }
    }

    public static List<SkillVM> toSkillVMList(List<Skills> skills) {
        return skills.stream().map(SkillVM::new).collect(Collectors.toList());
    }
}
