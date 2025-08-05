package com.hei.school.repository;

import com.hei.school.model.Help;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelpRepository extends JpaRepository<Help, UUID> {}
