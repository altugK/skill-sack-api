package com.skillsack.ws.departments.vm;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class DepartmentSubmitVM {

    @Size(min=1, max=50)
    @NotNull
    private String name;
}
