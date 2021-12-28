package com.skillsack.ws.skills;

import com.skillsack.ws.shared.GenericResponse;
import com.skillsack.ws.skills.vm.SkillSubmitVM;
import com.skillsack.ws.skills.vm.SkillVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/1.0")
public class SkillController {

    @Autowired
    SkillService skillService;

    @PostMapping("/skill")
    GenericResponse saveSkill(@Valid @RequestBody SkillSubmitVM skill) {
        skillService.save(skill);
        return new GenericResponse("Skill is saved");
    }

    @GetMapping("/skills")
    Page<SkillVM> getSkills(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable page) {
        return skillService.getSkills(page).map(SkillVM::new);
    }

    @DeleteMapping("/skill/{id}")
    GenericResponse deleteSkill(@PathVariable(value = "id") long id) {
        skillService.deleteSkill(id);
        return new GenericResponse("Skill is deleted");
    }

    @PutMapping("/skill/{id}")
    SkillVM updateSkill(@PathVariable(value = "id") long id, @Valid @RequestBody SkillSubmitVM updatedSkill) {
        Skills skill = skillService.updateSkill(id, updatedSkill);
        return new SkillVM(skill);
    }

    @GetMapping("/skills/{name}")
    Page<SkillVM> getSkillsByName(@PathVariable(value = "name") String name,
                                  @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable page) {
        return skillService.getSkillsByName(name, page).map(SkillVM::new);
    }

}
