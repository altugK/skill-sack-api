package com.skillsack.ws.departments;

import com.skillsack.ws.employees.Employees;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Departments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    @NotNull
    @Pattern(regexp = "[^A-Za-z0-9]+")
    private String name;

    @OneToMany(mappedBy = "department", cascade = {CascadeType.PERSIST})
    private List<Employees> employees;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @PreRemove
    private void preRemove() {
        employees.forEach(child -> child.setDepartment(null));
    }

}
