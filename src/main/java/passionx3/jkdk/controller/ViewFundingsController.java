package passionx3.jkdk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import passionx3.jkdk.domain.Funding;
import passionx3.jkdk.service.jkdkFacade;

public class ViewFundingsController {

	@Autowired
	private jkdkFacade jkdkStore;
	
	@RequestMapping("/item/viewFundings.do")
	public ModelAndView handleRequest(@RequestParam("themeId") int themeId) throws Exception {
		
		List<Funding> fundingList = null;
		
		if (themeId != -1) {	// theme 선택 안됨
			fundingList = jkdkStore.getFundingItemList();
		} else {	// theme 선택됨, device 선택됨
			fundingList = jkdkStore.getFundingItemList(themeId);
		}
		
		return new ModelAndView("viewFundings", "fundingList", fundingList);
	}
}
