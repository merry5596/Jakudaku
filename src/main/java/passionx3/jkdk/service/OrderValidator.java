package passionx3.jkdk.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import passionx3.jkdk.domain.Order;

@Component
public class OrderValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Order.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		validateCreditCard((Order) obj, errors);
	}
	
	public void validateCreditCard(Order order, Errors errors) {
		errors.setNestedPath("order");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCard", "CCN_REQUIRED", "FAKE (!) credit card number required.");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expireDate", "EXPIRY_DATE_REQUIRED", "Expiry date is required.");

		// Validate expireDate
		String expireDate = order.getExpireDate();
		
		if (expireDate == null || expireDate.trim().isEmpty()) {	// null
			errors.rejectValue("expireDate", "EXPIRY_DATE_REQUIRED", "Expiry date is required.");
		}
		else if (!Pattern.matches("^(0[1-9]|1[0-2])/[0-9]{2}$", expireDate)) {	// type error
			errors.rejectValue("expireDate", "EXPIRY_DATE_INVALID", "Expire Date type is wrong.");
		} else {
			SimpleDateFormat format = new SimpleDateFormat("MM/yy");
				String today = format.format(new Date());

				if (expireDate.compareTo(today) < 0) {	// is expired
					errors.rejectValue("expireDate", "EXPIRY_DATE_EXPIRED", "Expired Date");
				}
		}
		
		if (order.getCardType().equals("notSelected")) {
			errors.rejectValue("cardType", "CARD_TYPE_REQUIRED", "Card type is required.");
		}
		
		
		errors.setNestedPath("");
	}

}

