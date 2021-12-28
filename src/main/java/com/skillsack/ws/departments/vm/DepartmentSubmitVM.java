package com.skillsack.ws.departments.vm;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class DepartmentSubmitVM {

    @Size(min = 3, max = 50)
    @NotNull
    @Pattern(regexp = "[^A-Za-z0-9]+", message = "Department name should not contain special characters")
    private String name;
}
