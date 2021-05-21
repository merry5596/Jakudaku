package passionx3.jkdk.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import passionx3.jkdk.controller.AccountForm;
import passionx3.jkdk.domain.Account;

@Service
public class AccountFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Account.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		AccountForm accountForm = (AccountForm)obj; 
		Account account = accountForm.getAccount();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.userId", 
		"FIRST_NAME_REQUIRED", "First name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.alias", 
		"LAST_NAME_REQUIRED", "Last name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.name", 
				"LAST_NAME_REQUIRED", "Last name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.email", "EMAIL_REQUIRED", 
		"Email address is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.phone", "PHONE_REQUIRED", 
		"Phone number is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.address1", 
		"ADDRESS_REQUIRED", "Address (1) is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.address2", 
				"ADDRESS_REQUIRED", "Address (1) is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.zip", "ZIP_REQUIRED", 
		"ZIP is required.");
		
		if (accountForm.isNewAccount()) {
			account.setStatus("OK");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.name", 
			"USER_ID_REQUIRED", "User ID is required.");
			if (account.getPassword() == null || account.getPassword().length() < 1 ||
			!account.getPassword().equals(accountForm.getRepeatedPassword())) {
				errors.reject("PASSWORD_MISMATCH",
				"Passwords did not match or were not provided. Matching passwords are required.");
			}
		}
		else if (account.getPassword() != null && account.getPassword().length() > 0) {
			if (!account.getPassword().equals(accountForm.getRepeatedPassword())) {
				errors.reject("PASSWORD_MISMATCH", "Passwords did not match. Matching passwords are required.");
			}
		}
	}
}


