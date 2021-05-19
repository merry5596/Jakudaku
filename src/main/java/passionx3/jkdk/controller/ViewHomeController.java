package passionx3.jkdk.controller;

import org.springframework.beans.support.PagedListHolder;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class ViewHomeController { 
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(
			) throws Exception {
		
		Calendar cal= Calendar.getInstance ( );
		int day_of_week = cal.get ( Calendar.DAY_OF_WEEK );

		if(day_of_week == 7) { // 토요일이면 battleSale set
			String itemId1;
			String itemId2;
			
			
		}
		else if(day_of_week == 8) { //일요일이면 battleSale 진행
		}
		else { //다른 요일에는 timeSale 진행
			
			
		}
		return "thyme/Home";
	}
}
