package passionx3.jkdk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import passionx3.jkdk.domain.Category;
import passionx3.jkdk.domain.Online;
import passionx3.jkdk.domain.Pagination;
import passionx3.jkdk.domain.Theme;
import passionx3.jkdk.service.jkdkFacade;

@Controller
@SessionAttributes("userSession")
public class ViewCategoryController {
	
	@Autowired
	private jkdkFacade jkdkStore;
	
	@RequestMapping("/item/viewCategory.do")
	public ModelAndView handleRequest(	
			@RequestParam("categoryId") int categoryId, 
			@RequestParam("themeId") int themeId,
			@RequestParam("device") int device,
			@RequestParam("sortBy") int sortBy,
			@RequestParam("page") int page,
			@RequestParam(value="keyword", required=false) String keyword) throws Exception {
				
		if (keyword == null) keyword = "";
	
		int itemPerPage = 16;
		int end = page * itemPerPage;
		int start = end - itemPerPage + 1;
		
		List<Online> onlineList = jkdkStore.getOnlineItemListByCategory(categoryId, themeId, device, keyword, sortBy, start, end);
		int totalItemCount = jkdkStore.getCountOfOnlineItemListByCategory(categoryId, themeId, keyword, start, end);
		
		Pagination pagination = new Pagination(page, itemPerPage, totalItemCount);
		
		Category category = jkdkStore.getCategoryByCategoryId(categoryId);
		
		List<Theme> allThemes = jkdkStore.getAllThemes();

		ModelAndView mav = new ModelAndView();
		mav.setViewName("thyme/item/ViewItemsByCategory");
		mav.addObject("allThemes", allThemes);
		mav.addObject("category", category);
		mav.addObject("themeId", themeId);
		mav.addObject("device", device);
		mav.addObject("keyword", keyword);
		mav.addObject("sortBy", sortBy);
		mav.addObject("onlineList", onlineList);
		mav.addObject("pagination", pagination);
		
		return mav;
	}
}