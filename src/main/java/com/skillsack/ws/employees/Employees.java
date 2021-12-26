package com.skillsack.ws.employees;

import com.skillsack.ws.departments.Departments;
import com.skillsack.ws.skills.Skills;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    @NotNull
    private String name;

    @ManyToOne
    @Nullable
    private Departments department;

    @ManyToMany
    @Nullable
    @JoinTable(name = "EMPLOYEE_MAP_SKILLS")
    private List<Skills> skills;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

}
