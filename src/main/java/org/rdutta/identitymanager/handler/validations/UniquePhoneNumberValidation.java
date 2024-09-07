package org.rdutta.identitymanager.handler.validations;

import lombok.RequiredArgsConstructor;
import org.rdutta.identitymanager.dto.sso_dto.EmployeeRequest;
import org.rdutta.identitymanager.exceptions.EmployeeException;
import org.rdutta.identitymanager.exceptions.ValidationException;
import org.rdutta.identitymanager.handler.implementation.AbstractEmployeeHandler;
import org.rdutta.identitymanager.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniquePhoneNumberValidation extends AbstractEmployeeHandler {
    private final EmployeeRepository employeeRepository;

    @Override
    public void handle(EmployeeRequest employeeRequest) throws Exception {
        if(employeeRepository.findByPhone(employeeRequest.getPhone()).isPresent()){
            throw new EmployeeException(ValidationException.isPhoneNumberExists);
        }
        passToNext(employeeRequest);
    }
}
