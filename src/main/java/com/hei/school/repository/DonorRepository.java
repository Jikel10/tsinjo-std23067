package com.hei.school.repository;

import com.hei.school.model.Donor;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, UUID> {
  Optional<Donor> findByEmail(String email);
}
