package org.rdutta.identitymanager.handler.validations;

import org.rdutta.identitymanager.dto.sso_dto.EmployeeRequest;
import org.rdutta.identitymanager.exceptions.EmployeeException;
import org.rdutta.identitymanager.exceptions.ValidationException;
import org.rdutta.identitymanager.handler.implementation.AbstractEmployeeHandler;
import org.rdutta.identitymanager.pattern.EmailFormat;
import org.springframework.stereotype.Component;

@Component
public class EmailFormatValidation extends AbstractEmployeeHandler {

    @Override
    public void handle(EmployeeRequest employeeRequest) throws Exception {
        if(employeeRequest.getEmail().matches(EmailFormat.EMAIL_REGEX)){
            throw new EmployeeException(ValidationException.inValidEmailFormat);
        }

        passToNext(employeeRequest);
    }
}
