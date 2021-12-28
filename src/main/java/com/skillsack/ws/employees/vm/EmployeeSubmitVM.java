package com.skillsack.ws.employees.vm;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class EmployeeSubmitVM {

    @Size(min = 3, max = 50)
    @NotNull
    @Pattern(regexp = "[a-zA-Z]+", message = "Employee name must not contain special characters")
    private String name;

    @Nullable
    private Long department;

    @Nullable
    private Long skill;

}
