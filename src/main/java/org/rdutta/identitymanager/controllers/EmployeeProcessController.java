package org.rdutta.identitymanager.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rdutta.identitymanager.dao.features.ProfileManagement;
import org.rdutta.identitymanager.processor.RequestProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeProcessController {
    private final RequestProcessor requestProcessor;
    @PostMapping("/process")
    public ResponseEntity<?> processUserRequest(@RequestBody @Valid ProfileManagement management) {
        try {
            Object result = requestProcessor.process(management);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Processing failed: " + e.getMessage());
        }
    }
}
