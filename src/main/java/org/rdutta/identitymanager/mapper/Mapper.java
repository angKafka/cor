package org.rdutta.identitymanager.mapper;

import org.rdutta.identitymanager.dto.sso_dto.EmployeeRequest;
import org.rdutta.identitymanager.enums.Roles;
import org.rdutta.identitymanager.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class Mapper {
    public Employee toEmployee(EmployeeRequest request){
        return Employee.builder()
                .firstName(request.getFirstName())
                .surname(request.getSurname())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .phone(request.getPhone())
                .role(Roles.valueOf(request.getRole()))
                .build();
    }
}
