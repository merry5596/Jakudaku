package passionx3.jkdk.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import passionx3.jkdk.controller.SellOnlineForm;
import passionx3.jkdk.domain.Online;
/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 * @modified by Changsup Park
 */
@Component
public class SellOnlineItemFormValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Online.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		SellOnlineForm sellOnlineForm = (SellOnlineForm)obj; 
		Online online = sellOnlineForm.getOnline();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "online.themeId", "THEME_REQUIRED", "테마를 선택하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "online.categoryId", "CATEGORY_REQUIRED", "카테고리를 선택하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "online.name", "NAME_REQUIRED", "상품명을 입력하세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "online.price", "PRICE_REQUIRED", "판매 가격을 입력하세요.");
	}
}