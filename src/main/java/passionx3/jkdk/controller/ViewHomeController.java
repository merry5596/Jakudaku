package passionx3.jkdk.controller;

import org.springframework.beans.support.PagedListHolder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
			String itemId1;
			String itemId2;
			
			//같은 카테고리 내에서 랜덤으로 itemId 2개 뽑기
			
			jkdkStore.insertBattleSale("5", "6", today, tomorrow);
			
		}
		else if(day_of_week == 8) { //일요일이면 battleSale 진행
			int itemId = jkdkStore.getWinnerItemId();//viewBattleSalecontroller에서 정의하고 보여주기
			
		}
		else { //다른 요일에는 timeSale 진행
			jkdkStore.insertTimeSale("5", today, tomorrow);
		}
		
		return "thyme/Home";
	}
	
	
	// 여기부터는 혜연이가 추가한 파트임 일단 복붙만 해두겠음!

	@RequestMapping("/home.do")
	public ModelAndView handleRequest() throws Exception {
		List<Online> bestOnlineList = jkdkStore.getBestOnlineItemList();
		List<Online> newOnlineList = jkdkStore.getNewOnlineItemList();
		List<Funding> newFundingList = jkdkStore.getNewFundingItemList();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		mav.addObject("bestOnlineList", bestOnlineList);
		mav.addObject("newOnlineList", newOnlineList);
		mav.addObject("newFundingList", newFundingList);
		
		return mav;
	}
	
}
