package passionx3.jkdk.service;

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
		validateAddress((Order) obj, errors);
	}
	
	public void validateCreditCard(Order order, Errors errors) {
		errors.setNestedPath("order");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCard", "CCN_REQUIRED", "FAKE (!) credit card number required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expiryDate", "EXPIRY_DATE_REQUIRED", "Expiry date is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardType", "CARD_TYPE_REQUIRED", "Card type is required.");
		errors.setNestedPath("");
	}

	private void validateAddress(Order ojb, Errors errors) {
		errors.setNestedPath("order");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receiverName", "RECEIVER_NAME_REQUIRED", "Billing Info: receiver name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address1", "ADDRESS1_REQUIRED", "Billing Info: address1 is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address2", "ADDRESS2_REQUIRED", "Billing Info: address2 is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "zip", "ZIP_REQUIRED", "Billing Info: zip is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "PHONE_REQUIRED", "Billing Info: phone is required.");
		errors.setNestedPath("");
	}

}

