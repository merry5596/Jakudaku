package passionx3.jkdk.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receiverName", "RECEIVER_NAME_REQUIRED", "받는 분 성함을 입력해주세요.");
		
		// zip
		String zip = fundOrder.getZip();
		if (zip == null || zip.trim().isEmpty()) {	// null
			errors.rejectValue("zip", "ZIP_REQUIRED", "우편번호를 입력해주세요.");
		}
		else if (!Pattern.matches("^\\d{5}$", zip)) {	// type error
			errors.rejectValue("zip", "ZIP_INVALID", "우편 번호 형식에 맞지 않습니다.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address1", "ADDRESS1_REQUIRED", "주소를 입력해주세요.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address2", "ADDRESS2_REQUIRED", "상세 주소를 입력해주세요.");
		
		// phone
		String phone = fundOrder.getPhone();
		if (phone == null || phone.trim().isEmpty()) {	// null
			errors.rejectValue("phone", "PHONE_REQUIRED", "휴대폰 번호를 입력해주세요.");
		}
		else if (!Pattern.matches("^01[0-1]\\d{3,4}\\d{4}$", phone)) {	// type error
			errors.rejectValue("phone", "PHONE_INVALID", "휴대폰 번호 형식에 맞지 않습니다.");
		}
		

		errors.setNestedPath("");
	}
	
	public void validateCreditCard(FundOrder fundOrder, Errors errors) {
		errors.setNestedPath("fundOrder");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCard", "CCN_REQUIRED", "FAKE (!) credit card number required.");
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expireDate", "EXPIRY_DATE_REQUIRED", "Expiry date is required.");
		
		// Validate creditCard(number)
				String creditCard = fundOrder.getCreditCard();
				
				if (creditCard == null || creditCard.trim().isEmpty()) {	// null
					errors.rejectValue("creditCard", "CCN_REQUIRED", "카드 번호를 입력해주세요.");
				}
				else if (!Pattern.matches("^\\d{16}$", creditCard)) {	// type error
					errors.rejectValue("creditCard", "CCN_INVALID", "카드 번호 형식에 맞지 않습니다.");
				}
				
		// Validate expireDate
		String expireDate = fundOrder.getExpireDate();
		
		if (expireDate == null || expireDate.trim().isEmpty()) {	// null
			errors.rejectValue("expireDate", "EXPIRY_DATE_REQUIRED", "카드 만료일을 입력해주세요.");
		}
		else if (!Pattern.matches("^(0[1-9]|1[0-2])/[0-9]{2}$", expireDate)) {	// type error
			errors.rejectValue("expireDate", "EXPIRY_DATE_INVALID", "카드 만료일의 형식에 맞지 않습니다.");
		} else {
			StringTokenizer st = new StringTokenizer(expireDate, "/");
			int expireMonth = Integer.parseInt(st.nextToken());
			int expireYear = Integer.parseInt(st.nextToken());
			
			SimpleDateFormat format = new SimpleDateFormat("MM/yy");
			String today = format.format(new Date());
			
			StringTokenizer st2 = new StringTokenizer(today, "/");
			int todayMonth = Integer.parseInt(st2.nextToken());
			int todayYear = Integer.parseInt(st2.nextToken());
			
			if (expireYear < todayYear || (expireYear == todayYear && expireMonth < todayMonth)) {	// is expired
				errors.rejectValue("expireDate", "EXPIRY_DATE_EXPIRED", "카드 만료일은 오늘 날짜보다 빠를 수 없습니다.");
			}
		}
		
		// Validate cardType
		if (fundOrder.getCardType().equals("notSelected")) {
			errors.rejectValue("cardType", "CARD_TYPE_REQUIRED", "카드 종류를 선택해주세요.");
		}
		
		
		errors.setNestedPath("");
	}

}
