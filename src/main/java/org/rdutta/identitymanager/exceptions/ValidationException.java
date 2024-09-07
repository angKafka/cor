package org.rdutta.identitymanager.exceptions;

public interface ValidationException {
    final String isUsernameExists = "username is already exists";
    final String isEmailExists = "email is already exists";
    final String inValidPhoneFormat = "phone format is not valid";
    final String isPhoneNumberExists = "phone number is already exists";
    final String inValidEmailFormat = "email format is not valid";
}
