package org.rdutta.identitymanager.dto.req_obj;

import org.rdutta.identitymanager.dto.sso_dto.EmployeeRequest;

import java.time.LocalDateTime;

public class ProfileManagement {
    private String operation;
    private EmployeeRequest employeeRequest;
    private LocalDateTime signedInTime;
}
