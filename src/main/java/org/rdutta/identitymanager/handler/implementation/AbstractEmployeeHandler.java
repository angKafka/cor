package org.rdutta.identitymanager.handler.implementation;

import org.rdutta.identitymanager.dto.sso_dto.EmployeeRequest;
import org.rdutta.identitymanager.handler.EmployeeHandler;

public abstract class AbstractEmployeeHandler implements EmployeeHandler {

    protected EmployeeHandler nextHandler;

    @Override
    public void setNext(EmployeeHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected void passToNext(EmployeeRequest employeeRequest) throws Exception{
        if(this.nextHandler != null) {
            this.nextHandler.handle(employeeRequest);
        }

    }
}
