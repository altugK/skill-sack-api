package com.skillsack.ws.skills;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skills, Long> {
    Optional<List<Skills>> findByIdIn(List<Long> skills);
}
