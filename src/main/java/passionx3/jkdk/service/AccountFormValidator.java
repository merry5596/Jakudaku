package passionx3.jkdk.service;

import java.util.regex.Pattern;

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
		"USERID_REQUIRED", "아이디를 입력해주세요");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.password", 
				"PASSWORD_REQUIRED", "비밀번호를 입력해주세요");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "repeatedPassword", 
				"REPEATEDPASSWORD_REQUIRED", "비밀번호를 다시 입력해주세요");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.alias", 
		"ALIAS_REQUIRED", "닉네임을 입력해주세요");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.name", 
				"NAME_REQUIRED", "이름을 입력해주세요");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.address1", 
		"ADDRESS_REQUIRED", "주소를 입력해주세요");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.address2", 
				"ADDRESS_REQUIRED", "주소를 입력해주세요");

		
		if (accountForm.isNewAccount()) {
			if (account.getPassword() == null || account.getPassword().length() < 1 ||
			!account.getPassword().equals(accountForm.getRepeatedPassword())) {
				errors.reject("PASSWORD_MISMATCH",
				"비밀번호가 일치하지 않습니다. 다시 입력해주세요");
			}
		}
		else if (account.getPassword() != null && account.getPassword().length() > 0) {
			if (!account.getPassword().equals(accountForm.getRepeatedPassword())) {
				errors.reject("PASSWORD_MISMATCH", "비밀번호가 일치하지 않습니다. 다시 입력해주세요");
			}
		}
		
		String zip = account.getZip();
		if (zip == null || zip.trim().isEmpty()) {	// null
			errors.rejectValue("account.zip", "ZIP_REQUIRED", "우편번호를 입력해주세요.");
		}
		else if (!Pattern.matches("^\\d{5}$", zip)) {	// type error
			errors.rejectValue("account.zip", "ZIP_INVALID", "우편 번호 형식에 맞지 않습니다. (00000)");
		}
		
		String phone = account.getPhone();
		if (phone == null || phone.trim().isEmpty()) {	// null
			errors.rejectValue("account.phone", "PHONE_REQUIRED", "휴대폰 번호를 입력해주세요.");
		}
		else if (!Pattern.matches("^01[0-1]\\d{3,4}\\d{4}$", phone)) {	// type error
			errors.rejectValue("account.phone", "PHONE_INVALID", "휴대폰 번호 형식에 맞지 않습니다. (01000000000)");
		}
		
		String email = account.getEmail();
		if (email == null || email.trim().isEmpty()) {	// null
			errors.rejectValue("account.email", "EMAIL_REQUIRED", "이메일을 입력해주세요.");
		}
		else if (!Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", email)) {	// type error
			errors.rejectValue("account.email", "EMAIL_INVALID", "이메일 형식에 맞지 않습니다.");
		}
	}
}


