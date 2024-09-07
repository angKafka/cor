package org.rdutta.identitymanager.dao.features;

import lombok.*;
import org.rdutta.identitymanager.dto.sso_dto.EmployeeRequest;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProfileManagement {
    private String operation;
    private EmployeeRequest employeeRequest;
    private UUID employeeId;
}
