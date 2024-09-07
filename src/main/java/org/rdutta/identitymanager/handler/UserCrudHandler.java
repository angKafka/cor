package org.rdutta.identitymanager.handler;

import lombok.RequiredArgsConstructor;
import org.rdutta.identitymanager.dao.features.ProfileManagement;
import org.rdutta.identitymanager.exceptions.EmployeeException;
import org.rdutta.identitymanager.exceptions.ErrorMessages;
import org.rdutta.identitymanager.model.Employee;
import org.rdutta.identitymanager.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserCrudHandler implements CRUDHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCrudHandler.class);

    private CRUDHandler nextHandler;
    private final EmployeeService employeeService;

    @Override
    public void setNext(CRUDHandler next) {
        this.nextHandler = next;
    }

    @Override
    public Object handle(ProfileManagement management) throws Exception {
        Object response = null;
        try {
            switch (management.getOperation()) {
                case "CREATE":
                    UUID createdEmployeeId = employeeService.createEmployee(management.getEmployeeRequest());
                    LOGGER.info("Employee Created with ID: {}", createdEmployeeId);
                    response = createdEmployeeId;
                    break;
                case "READ":
                    Employee employee = employeeService.getEmployee(String.valueOf(management.getEmployeeId()));
                    LOGGER.info("Employee Retrieved: {}", employee);
                    response = employee;
                    break;
                case "UPDATE":
                    employeeService.updateEmployee(management.getEmployeeId(), management.getEmployeeRequest());
                    LOGGER.info("Employee Updated");
                    response = "Employee Updated Successfully";
                    break;
                case "DELETE":
                    employeeService.deleteEmployee(management.getEmployeeId());
                    LOGGER.info("Employee Deleted");
                    response = "Employee Deleted Successfully";
                    break;
                default:
                    throw new EmployeeException(ErrorMessages.INVALID_OPERATION);
            }
        } catch (EmployeeException e) {
            LOGGER.error("Error handling operation {} for employee ID {}: {}",
                    management.getOperation(),
                    management.getEmployeeId(),
                    e.getMessage());
            throw e;
        }

        if (nextHandler != null) {
            response = nextHandler.handle(management);
        }
        return response;
    }
}
