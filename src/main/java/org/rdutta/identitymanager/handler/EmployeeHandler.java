package org.rdutta.identitymanager.handler;

import org.rdutta.identitymanager.dto.sso_dto.EmployeeRequest;

public interface EmployeeHandler {
    void handle(EmployeeRequest employeeRequest) throws Exception;
    void setNext(EmployeeHandler nextHandler);
}
