package com.web.userservice.repository;

import com.web.userservice.entity.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, UUID> {
}
