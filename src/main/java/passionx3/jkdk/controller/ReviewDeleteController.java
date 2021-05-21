package passionx3.jkdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import passionx3.jkdk.domain.Review;
import passionx3.jkdk.service.jkdkFacade;

@Controller
public class ReviewDeleteController {

	private jkdkFacade jkdkStore;
	
	@Autowired
	public void setJkdkStore(jkdkFacade jkdkStore) {
		this.jkdkStore = jkdkStore;
	}
	
	@RequestMapping("/review/removeReview.do")
	public String handleRequest(@RequestParam("reviewId") int reviewId) throws Exception {
		jkdkStore.deleteReview(reviewId);
		return "redirect:/user/myPage/buy.do";
	}
}
