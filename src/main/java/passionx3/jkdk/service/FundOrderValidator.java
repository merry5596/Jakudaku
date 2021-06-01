package passionx3.jkdk.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import passionx3.jkdk.domain.FundOrder;
import passionx3.jkdk.domain.Order;

@Component
public class FundOrderValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Order.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		validateAddress((FundOrder) obj, errors);
		validateCreditCard((FundOrder) obj, errors);
	}

	public void validateAddress(FundOrder fundOrder, Errors errors) {
		errors.setNestedPath("fundOrder");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receiverName", "RECEIVER_NAME_REQUIRED", "Billing Info: receiver name is required.");
		
		// zip
		String zip = fundOrder.getZip();
		if (zip == null || zip.trim().isEmpty()) {	// null
			errors.rejectValue("zip", "ZIP_REQUIRED", "Billing Info: zip is required.");
		}
		else if (!Pattern.matches("^\\d{5}$", zip)) {	// type error
			errors.rejectValue("zip", "ZIP_INVALID", "Billing Info: zip type is wrong.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address1", "ADDRESS1_REQUIRED", "Billing Info: address1 is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address2", "ADDRESS2_REQUIRED", "Billing Info: address2 is required.");
		
		// phone
		String phone = fundOrder.getPhone();
		if (phone == null || phone.trim().isEmpty()) {	// null
			errors.rejectValue("phone", "PHONE_REQUIRED", "Billing Info: phone is required.");
		}
		else if (!Pattern.matches("^01[0-1]\\d{3,4}\\d{4}$", phone)) {	// type error
			errors.rejectValue("phone", "PHONE_INVALID", "Billing Info: phone type is wrong.");
		}
		

		errors.setNestedPath("");
	}
	
	public void validateCreditCard(FundOrder fundOrder, Errors errors) {
		errors.setNestedPath("fundOrder");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCard", "CCN_REQUIRED", "FAKE (!) credit card number required.");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expireDate", "EXPIRY_DATE_REQUIRED", "Expiry date is required.");

		// Validate expireDate
		String expireDate = fundOrder.getExpireDate();
		
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
		
		if (fundOrder.getCardType().equals("notSelected")) {
			errors.rejectValue("cardType", "CARD_TYPE_REQUIRED", "Card type is required.");
		}
		
		
		errors.setNestedPath("");
	}

}
