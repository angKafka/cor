package org.rdutta.identitymanager.handler.validations;

import lombok.RequiredArgsConstructor;
import org.rdutta.identitymanager.dto.sso_dto.EmployeeRequest;
import org.rdutta.identitymanager.exceptions.ValidationException;
import org.rdutta.identitymanager.handler.implementation.AbstractEmployeeHandler;
import org.rdutta.identitymanager.pattern.IndianFormat;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IndianNumberValidation extends AbstractEmployeeHandler {
    @Override
    public void handle(EmployeeRequest employeeRequest) throws Exception {
        if(employeeRequest.getPhone().matches(IndianFormat.INDIAN_NUMBER_REGEX)){
            throw new Exception(ValidationException.inValidPhoneFormat);
        }
    }
}
