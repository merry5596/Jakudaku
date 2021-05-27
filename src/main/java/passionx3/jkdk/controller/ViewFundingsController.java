package passionx3.jkdk.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import passionx3.jkdk.domain.Funding;
import passionx3.jkdk.service.jkdkFacade;

@Controller
@SessionAttributes("userSession")
public class ViewFundingsController {

	@Autowired
	private jkdkFacade jkdkStore;
	
	@RequestMapping("/item/viewFundings.do")
	public ModelAndView handleRequest(@RequestParam("themeId") int themeId) throws Exception {
		SimpleDateFormat toFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
	
		
		List<Funding> fundingList = null;
		
		if (themeId == -1) {	// theme 선택 안됨
			fundingList = jkdkStore.getFundingItemList();
			
			
		} else {	// theme 선택됨, device 선택됨
			fundingList = jkdkStore.getFundingItemListByTheme(themeId);
		}
		
		// finish Date 형식 바꿔주
//		for (Funding item : fundingList) {
//			String findate = item.getFinishDate();
//			Date finish = toFormat.parse(findate);
//			
//			long gap = finish.getTime() - today.getTime();
//			long diffDays = gap / (24 * 60 * 60 * 1000);
//			
//			item.setFinishDate(Long.toString(diffDays));
//		}
		

		List<Theme> allThemes = jkdkStore.getAllThemes();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("thyme/item/ViewFundings");
		mav.addObject("allThemes", allThemes);
		mav.addObject("themeId", themeId);
		mav.addObject("fundingList", fundingList);
		
		return mav;
	}
}
