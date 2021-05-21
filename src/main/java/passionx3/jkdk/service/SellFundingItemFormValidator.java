package passionx3.jkdk.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import passionx3.jkdk.controller.SellFundingForm;
import passionx3.jkdk.domain.Funding;
/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 * @modified by Changsup Park
 */
@Component
public class SellFundingItemFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Funding.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		SellFundingForm sellFundingForm = (SellFundingForm)obj; 
		Funding funding = sellFundingForm.getFunding();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "funding.themeId", "THEME_REQUIRED", "테마를 선택하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "funding.categoryId", "CATEGORY_REQUIRED", "카테고리를 선택하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "funding.name", "NAME_REQUIRED", "상품명을 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "funding.price", "PRICE_REQUIRED", "판매 가격을 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "funding.date", "FINISHDATE_REQUIRED", "펀딩 종료 날짜/시간을 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "funding.targetQuantity", "TARGETQUANTITY_REQUIRED", "목표 수량을 입력하세요.");
	}
}