package com.skillsack.ws.departments;

import com.skillsack.ws.employees.Employees;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
