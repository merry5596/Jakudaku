package passionx3.jkdk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import passionx3.jkdk.domain.Online;
import passionx3.jkdk.service.jkdkFacade;

@Controller
@SessionAttributes("userSession")
public class ViewCategoryController {
	
	@Autowired
	private jkdkFacade jkdkStore;
	
	@RequestMapping("/item/viewCategory.do")
	public ModelAndView handleRequest(			
			@RequestParam("categoryId") String categoryId, 
			@RequestParam("themeId") int themeId,
			@RequestParam("device") int device) throws Exception {
		
		List<Online> onlineList = null;
		
		if (themeId != -1 && device != -1) {	// theme 선택 안됨, device 선택 안됨
			onlineList = jkdkStore.getOnlineItemListByCategory(categoryId);
		} else if (themeId != -1 && device == -1) {	// theme 선택됨, device 선택 안됨
			onlineList = jkdkStore.getOnlineItemListByCategory(categoryId, themeId);
		} else if (themeId == -1 && device != -1) {	// theme 선택 안됨, device 선택됨
			onlineList = jkdkStore.getOnlineItemListByCategory2(categoryId, device);	// themeId랑 device 타입이 같아서 오버로딩이 안돼..
		} else {	// theme 선택됨, device 선택됨
			onlineList = jkdkStore.getOnlineItemListByCategory(categoryId, themeId, device);
		}
		
		String categoryName = jkdkStore.getCategoryByCategoryId(categoryId).getName();
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("thyme/ViewItemsByCategory");
		mav.addObject("categoryName", categoryName);
		mav.addObject("themeId", themeId);
		mav.addObject("device", device);
		mav.addObject("onlineList", onlineList);
		
		return mav;
	}
}
