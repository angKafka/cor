package org.rdutta.identitymanager.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.rdutta.identitymanager.dao.employee.EmployeeDao;
import org.rdutta.identitymanager.dto.sso_dto.EmployeeRequest;
import org.rdutta.identitymanager.exceptions.EmployeeException;
import org.rdutta.identitymanager.exceptions.NotFoundMessages;
import org.rdutta.identitymanager.handler.validations.*;
import org.rdutta.identitymanager.mapper.Mapper;
import org.rdutta.identitymanager.model.Employee;
import org.rdutta.identitymanager.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService implements EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository repository;
    private final Mapper mapper;
    private final EmailFormatValidation emailFormatValidation;
    private final IndianNumberValidation indianNumberValidation;
    private final UniqueEmailValidation uniqueEmailValidation;
    private final UniquePhoneNumberValidation uniquePhoneNumberValidation;
    private final UniqueUsernameValidation uniqueUsernameValidation;

    @Transactional
    @Override
    public UUID createEmployee(EmployeeRequest request) throws Exception {
        validateEmployeeRequest(request);
        Employee employee = mapper.toEmployee(request);
        return repository.save(employee).getEmp_id();
    }

    @Override
    public Employee getEmployee(String employeeId) {
        return repository.findById(UUID.fromString(employeeId))
                .orElseThrow(() -> new EmployeeException(NotFoundMessages.EMPLOYEE_NOT_FOUND));
    }

    @Transactional
    @Override
    public void updateEmployee(UUID employeeId, EmployeeRequest request) {
        Employee existingEmp = repository.findById(employeeId)
                .orElseThrow(() -> new EmployeeException(NotFoundMessages.EMPLOYEE_NOT_FOUND));
        existingEmp.setUsername(request.getUsername());
        existingEmp.setEmail(request.getEmail());
        repository.save(existingEmp);
        LOGGER.info("User with ID {} updated", employeeId);
    }

    @Transactional
    @Override
    public void deleteEmployee(UUID employeeId) {
        if (!repository.existsById(employeeId)) {
            throw new EmployeeException(NotFoundMessages.EMPLOYEE_NOT_FOUND);
        }
        repository.deleteById(employeeId);
        LOGGER.info("User with ID {} deleted", employeeId);
    }

    private void validateEmployeeRequest(EmployeeRequest request) throws Exception {
        emailFormatValidation.setNext(indianNumberValidation);
        indianNumberValidation.setNext(uniqueEmailValidation);
        uniqueEmailValidation.setNext(uniquePhoneNumberValidation);
        uniquePhoneNumberValidation.setNext(uniqueUsernameValidation);
        uniqueUsernameValidation.handle(request);
    }
}
