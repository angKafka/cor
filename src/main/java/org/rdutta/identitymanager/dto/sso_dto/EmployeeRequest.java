package org.rdutta.identitymanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.rdutta.identitymanager.exceptions.NullCheckMessages;
import org.rdutta.identitymanager.exceptions.ValidationException;
import org.rdutta.identitymanager.pattern.EmailFormat;
import org.rdutta.identitymanager.pattern.IndianFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    @NotNull(message = NullCheckMessages.firstNameNotNull)
    private String firstName;
    @NotNull(message = NullCheckMessages.surNameNotNull)
    private String surname;
    @NotNull(message = NullCheckMessages.surNameNotNull)
    private String username;
    @NotNull(message = NullCheckMessages.emailNotNull)
    @Email(regexp = EmailFormat.EMAIL_REGEX, message = ValidationException.inValidEmailFormat)
    private String email;
    @NotNull(message = NullCheckMessages.phoneNotNull)
    @Pattern(regexp = IndianFormat.INDIAN_NUMBER_REGEX, message = ValidationException.inValidPhoneFormat)
    private String phone;
    @NotNull(message = NullCheckMessages.passwordNotNull)
    private String password;
    @NotNull(message = NullCheckMessages.roleNotNull)
    private String role;
}
