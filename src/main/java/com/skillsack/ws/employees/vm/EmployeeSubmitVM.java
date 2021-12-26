package com.skillsack.ws.employees.vm;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EmployeeSubmitVM {

    @Size(min = 1, max = 50)
    @NotNull
    private String name;

    @Nullable
    private Long department;

    @Nullable
    private Long skill;

}
