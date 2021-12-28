package com.skillsack.ws.skills;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SkillRepository extends JpaRepository<Skills, Long> {

    Page<Skills> findByNameContains(String name, Pageable page);
}
