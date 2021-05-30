package passionx3.jkdk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import passionx3.jkdk.domain.Funding;
import passionx3.jkdk.domain.Theme;
import passionx3.jkdk.service.jkdkFacade;

@Controller
@SessionAttributes("userSession")
public class ViewFundingsController {

	@Autowired
	private jkdkFacade jkdkStore;

	@RequestMapping("/item/viewFundings.do")
	public ModelAndView handleRequest(
			@RequestParam("themeId") int themeId, 
			@RequestParam("sortBy") int sortBy,
			@RequestParam(value="keyword", required=false) String keyword) throws Exception {
		
		System.out.println(keyword);

		if (keyword == null) keyword = "";
		
		System.out.println("/item/viewFundings.do");
		
		List<Funding> fundingList = jkdkStore.getFundingItemList(themeId, keyword, sortBy);
		
		List<Theme> allThemes = jkdkStore.getAllThemes();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("thyme/item/ViewFundings");
		mav.addObject("allThemes", allThemes);
		mav.addObject("themeId", themeId);
		mav.addObject("keyword", keyword);
		mav.addObject("sortBy", sortBy);
		mav.addObject("fundingList", fundingList);
		
		return mav;
	}
}
