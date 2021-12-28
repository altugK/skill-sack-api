package com.skillsack.ws.skills;

import com.skillsack.ws.skills.vm.SkillSubmitVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SkillService {

    SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public void save(SkillSubmitVM skill) {
        Skills skills = new Skills();
        skills.setName(skill.getName());
        skills.setTimestamp(new Date());
        skillRepository.save(skills);
    }

    public Page<Skills> getSkills(Pageable page) {
        return skillRepository.findAll(page);
    }

    public void deleteSkill(long id) {
        skillRepository.deleteById(id);
    }

    public Skills updateSkill(long id, SkillSubmitVM updatedSkill) {
        Skills skills = skillRepository.findById(id).get();
        skills.setName(updatedSkill.getName());
        skills.setTimestamp(new Date());
        return skillRepository.save(skills);
    }

    public Page<Skills> getSkillsByName(String name, Pageable page) {
        return skillRepository.findByNameContains(name, page);
    }
}
