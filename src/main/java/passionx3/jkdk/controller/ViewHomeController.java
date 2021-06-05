package passionx3.jkdk.controller;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import passionx3.jkdk.domain.Funding;
import passionx3.jkdk.domain.Online;
import passionx3.jkdk.service.jkdkFacade;


@Controller
@SessionAttributes("userSession")
public class ViewHomeController { 
	
	@Autowired
	private jkdkFacade jkdkStore;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(ModelMap model) throws Exception {
		
		Calendar cal= Calendar.getInstance ( );
		int day_of_week = cal.get ( Calendar.DAY_OF_WEEK );
		
		SimpleDateFormat sDate = new SimpleDateFormat("yy/MM/dd");
		cal.setTime(new Date());
		String today = sDate.format(cal.getTime());
		
		jkdkStore.updateNotSale(today);//세일기간이 지나면 state 변경
		
		if(day_of_week == 7) { // 토요일이면 battleSale set
			jkdkStore.insertBattleSale();
		}
		else if(day_of_week != 1){ //평일에는 timeSale 진행
			jkdkStore.insertTimeSale();
		}
		
		List<Online> bestOnlineList = jkdkStore.getBestOnlineItemListforHome();
		List<Online> newOnlineList = jkdkStore.getNewOnlineItemListforHome();
		List<Funding> newFundingList = jkdkStore.getNewFundingItemListforHome(today);

		model.put("bestOnlineList", bestOnlineList);
		model.put("newOnlineList", newOnlineList);
		model.put("newFundingList", newFundingList);

		return "thyme/Home";
	}
	
	
}
