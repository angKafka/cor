package org.rdutta.identitymanager.handler.validations;

import org.rdutta.identitymanager.dao.features.ProfileManagement;
import org.rdutta.identitymanager.handler.UserCrudHandler;
import org.rdutta.identitymanager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperationValidationHandler extends UserCrudHandler {

    private UserCrudHandler nextHandler;

    @Autowired
    public OperationValidationHandler(EmployeeService employeeService) {
        super(employeeService);
    }

    @Override
    public Object handle(ProfileManagement management) throws Exception {

        if (!isValidOperation(management.getOperation())) {
            throw new RuntimeException("Invalid Operation: " + management.getOperation());
        }

        if (nextHandler != null) {
            nextHandler.handle(management);
        }
        return null;
    }

    // Helper method to validate the operation
    private boolean isValidOperation(String operation) {
        return operation.equalsIgnoreCase("CREATE") ||
                operation.equalsIgnoreCase("READ") ||
                operation.equalsIgnoreCase("UPDATE") ||
                operation.equalsIgnoreCase("DELETE");
    }
}

