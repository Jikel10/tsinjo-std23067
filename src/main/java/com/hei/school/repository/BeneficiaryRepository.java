package com.hei.school.repository;

import com.hei.school.model.Beneficiary;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, UUID> {
  Optional<Beneficiary> findByEmail(String email);
}
