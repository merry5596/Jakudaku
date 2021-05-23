package passionx3.jkdk.controller;

import org.springframework.beans.support.PagedListHolder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

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
		cal.add(Calendar.DATE, 1);
		String tomorrow = sDate.format(cal.getTime());
		
		if(day_of_week == 7) { // 토요일이면 battleSale set
			//같은 카테고리 내에서 랜덤으로 itemId 2개 뽑기
			int category = cal.get(Calendar.DAY_OF_MONTH) % 4;
			String item1 = jkdkStore.getOnlineItemIdByCategoryforSale(category);
			String item2 = jkdkStore.getOnlineItemIdByCategoryforSale(category);
			jkdkStore.insertBattleSale(item1, item2, today, tomorrow);
		}
		else if(day_of_week != 1){ //평일에는 timeSale 진행
			int category = cal.get(Calendar.DAY_OF_MONTH) % 4;
			String item = jkdkStore.getOnlineItemIdByCategoryforSale(category);
			jkdkStore.insertTimeSale(item, today, tomorrow);
		}
		
		List<Online> bestOnlineList = jkdkStore.getBestOnlineItemListforHome();
		List<Online> newOnlineList = jkdkStore.getNewOnlineItemListforHome();
		List<Funding> newFundingList = jkdkStore.getNewFundingItemListforHome();

		for (Online bestOnline : bestOnlineList) {
			bestOnline.setCategoryName(jkdkStore.getCategoryNameByCategoryId(bestOnline.getCategoryId()));
		 }
		
		for (Online newOnline : newOnlineList) {
			newOnline.setCategoryName(jkdkStore.getCategoryNameByCategoryId(newOnline.getCategoryId()));
		 }
		
		for (Funding newFunding : newFundingList) {
			newFunding.setCategoryName(jkdkStore.getCategoryNameByCategoryId(newFunding.getCategoryId()));
		 }
		
		model.put("bestOnlineList", bestOnlineList);
		model.put("newOnlineList", newOnlineList);
		model.put("newFundingList", newFundingList);

		return "thyme/Home";
	}
	
	
}
