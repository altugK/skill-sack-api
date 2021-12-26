package com.skillsack.ws.skills;

import com.skillsack.ws.employees.Employees;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    @NotNull
    private String name;

    @ManyToMany
    @Nullable
    @JoinTable(name = "EMPLOYEE_MAP_SKILLS")
    private List<Employees> employees;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
}
