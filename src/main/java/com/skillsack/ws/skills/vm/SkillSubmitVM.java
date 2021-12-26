package com.skillsack.ws.skills.vm;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SkillSubmitVM {

    @Size(min=1, max=50)
    @NotNull
    private String name;
}
