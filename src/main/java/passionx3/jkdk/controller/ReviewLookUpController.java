package passionx3.jkdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import passionx3.jkdk.domain.Review;
import passionx3.jkdk.service.jkdkFacade;

@Controller
public class ReviewLookUpController {

	private jkdkFacade jkdkStore;
	
	@Autowired
	public void setJkdkStore(jkdkFacade jkdkStore) {
		this.jkdkStore = jkdkStore;
	}
	
	@RequestMapping("/review/viewReview.do")
	public String handleRequest(@RequestParam("itemId") int itemId, ModelMap model) throws Exception {
		PagedListHolder<Review> reviewList = new PagedListHolder<Review>(this.jkdkStore.getReviewsByItemId(itemId));
		reviewList.setPageSize(5);
		model.put("reviewList", reviewList);
		return "/viewReview";
	}
}
