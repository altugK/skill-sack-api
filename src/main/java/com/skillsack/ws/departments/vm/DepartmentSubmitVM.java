package com.skillsack.ws.departments.vm;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class DepartmentSubmitVM {

    @Size(min = 3, max = 50)
    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9\\\\ ]+", message = "Skill name must not contain special characters")
    private String name;
}
