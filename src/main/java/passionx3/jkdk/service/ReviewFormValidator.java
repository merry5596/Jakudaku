package passionx3.jkdk.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import passionx3.jkdk.controller.AccountForm;
import passionx3.jkdk.controller.ReviewForm;
import passionx3.jkdk.domain.Account;
import passionx3.jkdk.domain.Review;

@Service
public class ReviewFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Review.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		ReviewForm reviewForm = (ReviewForm)obj; 
		Review review = reviewForm.getReview();
		
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
	}
}
