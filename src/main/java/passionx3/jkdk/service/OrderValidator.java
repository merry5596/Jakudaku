package passionx3.jkdk.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
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
		
		String creditCard = order.getCreditCard();
		
		if (creditCard == null || creditCard.trim().isEmpty()) {
			errors.rejectValue("creditCard", "CCN_REQUIRED", "카드 번호를 입력해주세요.");
		}
		else if (!Pattern.matches("^\\d{16}$", creditCard)) {
			errors.rejectValue("creditCard", "CCN_INVALID", "카드 번호 형식에 맞지 않습니다.");
		}
		
		String expireDate = order.getExpireDate();
		
		if (expireDate == null || expireDate.trim().isEmpty()) {
			errors.rejectValue("expireDate", "EXPIRY_DATE_REQUIRED", "카드 만료일을 입력해주세요.");
		}
		else if (!Pattern.matches("^(0[1-9]|1[0-2])/[0-9]{2}$", expireDate)) {
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
			
			if (expireYear < todayYear || (expireYear == todayYear && expireMonth < todayMonth)) {
				errors.rejectValue("expireDate", "EXPIRY_DATE_EXPIRED", "카드 만료일은 오늘 날짜보다 빠를 수 없습니다.");
			}
		}
		
		if (order.getCardType().equals("notSelected")) {
			errors.rejectValue("cardType", "CARD_TYPE_REQUIRED", "카드 종류를 선택해주세요.");
		}
		
		
		errors.setNestedPath("");
	}

}

