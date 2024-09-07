package org.rdutta.identitymanager.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rdutta.identitymanager.dao.features.ProfileManagement;
import org.rdutta.identitymanager.exceptions.EmployeeException;
import org.rdutta.identitymanager.handler.UserCrudHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestProcessor {

    private final UserCrudHandler userCrudHandler;

    public Object process(ProfileManagement management) throws Exception {
        Object result = null;
        try {
            result = userCrudHandler.handle(management);
        } catch (EmployeeException e) {
            log.error("Error processing request: {}", e.getMessage());
            throw new EmployeeException(e.getMessage());
        }
        return result;
    }
}
