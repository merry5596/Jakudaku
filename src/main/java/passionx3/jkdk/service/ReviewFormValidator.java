package passionx3.jkdk.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import passionx3.jkdk.domain.Review;

@Service
public class ReviewFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Review.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "review.rate", 
		"FIRST_NAME_REQUIRED", "평점을 입력해주세요");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "review.content", 
		"LAST_NAME_REQUIRED", "내용을 입력해주세요");
		
	}
}
