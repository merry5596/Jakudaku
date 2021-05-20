package passionx3.jkdk.service;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import passionx3.jkdk.controller.ReviewRegistRequest;

public class ReviewFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ReviewRegistRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ReviewRegistRequest regReq = (ReviewRegistRequest) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required");
		// 전화번호, 곡명을 입력하지 않으면 오류메세지 출력
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "song", "required");
	}
}
