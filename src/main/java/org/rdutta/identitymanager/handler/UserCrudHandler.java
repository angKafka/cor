package org.rdutta.identitymanager.handler.implementation;

import lombok.RequiredArgsConstructor;
import org.rdutta.identitymanager.dao.features.ProfileManagement;
import org.rdutta.identitymanager.dto.sso_dto.EmployeeRequest;
import org.rdutta.identitymanager.handler.CRUDHandler;
import org.rdutta.identitymanager.handler.EmployeeHandler;
import org.rdutta.identitymanager.mapper.Mapper;
import org.rdutta.identitymanager.model.Employee;
import org.rdutta.identitymanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCrudHandler implements CRUDHandler {
    private CRUDHandler nextHandler;

    private final EmployeeRepository empRepository;
    private final Mapper mapper;

    @Override
    public void setNext(CRUDHandler next) {
        this.nextHandler = next;
    }

    @Override
    public void handle(ProfileManagement management) throws Exception {
        switch (management.getOperation()) {
            case "CREATE":
                Employee employee = mapper.toEmployee(management.getEmployeeRequest());
                empRepository.save(employee);
                System.out.println("Employee Created");
                break;
            case "READ":
                empRepository.findById(management);
                System.out.println("User Retrieved");
                break;
            case "UPDATE":
                User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
                user.setUsername(request.getUser().getUsername());
                user.setEmail(request.getUser().getEmail());
                userRepository.save(user);
                System.out.println("User Updated");
                break;
            case "DELETE":
                userRepository.deleteById(request.getUserId());
                System.out.println("User Deleted");
                break;
            default:
                throw new RuntimeException("Invalid Operation");
        }
        if (nextHandler != null) {
            nextHandler.handle(management);
        }
    }
}
