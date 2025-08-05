package com.hei.school.repository;

import com.hei.school.model.Donation;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, UUID> {}
