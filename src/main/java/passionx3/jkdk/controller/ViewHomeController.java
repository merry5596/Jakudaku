package passionx3.jkdk.controller;

import org.springframework.beans.support.PagedListHolder;

import java.util.Calendar;
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
	
	
	// 여기부터는 혜연이가 추가한 파트임 일단 복붙만 해두겠음!
	@Autowired
	private jkdkFacade jkdkStore;

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
