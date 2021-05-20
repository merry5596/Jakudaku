package passionx3.jkdk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import passionx3.jkdk.domain.Funding;
import passionx3.jkdk.domain.Online;
import passionx3.jkdk.service.jkdkFacade;

@Controller
@RequestMapping("/item/searchItems.do")
public class SearchItemsController {
	
	@Autowired
	private jkdkFacade jkdkStore;
	
	public ModelAndView handleRequest(@RequestParam("keyword") String keyword) throws Exception {
		List<Online> onlineList = jkdkStore.getOnlineItemsByKeyword(keyword);
		List<Funding> fundingList = jkdkStore.getFundingItemsByKeyword(keyword);
	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("thyme/ViewCategory");
		mav.addObject("onlineList", onlineList);
		mav.addObject("fundingList", fundingList);
		return mav;
	}
}
