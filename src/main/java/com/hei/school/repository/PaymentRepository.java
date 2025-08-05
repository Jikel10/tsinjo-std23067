package com.hei.school.repository;

import com.hei.school.model.Payment;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
  List<Payment> findByStatus(String status);
}
