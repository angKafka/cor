package org.rdutta.identitymanager.dao.employee;

import org.rdutta.identitymanager.dto.sso_dto.EmployeeRequest;
import org.rdutta.identitymanager.model.Employee;

import java.util.UUID;

public interface EmployeeDao {
    UUID createEmployee(EmployeeRequest request) throws Exception;
    Employee getEmployee(String employeeId);
    void updateEmployee(UUID employeeId, EmployeeRequest request);
    void deleteEmployee(UUID employeeId);
}
