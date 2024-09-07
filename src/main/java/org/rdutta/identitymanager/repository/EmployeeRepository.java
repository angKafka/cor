package org.rdutta.identitymanager.repository;

import org.rdutta.identitymanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findByUsername(String username);
    Optional<Employee> findByPhone(String phone);
    boolean existsByEmail(String email);
}
