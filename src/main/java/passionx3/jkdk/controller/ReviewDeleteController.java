package passionx3.jkdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import passionx3.jkdk.service.jkdkFacade;
import passionx3.jkdk.util.Message;

@Controller
@SessionAttributes("userSession")
public class ReviewDeleteController {

	private jkdkFacade jkdkStore;
	
	@Autowired
	public void setJkdkStore(jkdkFacade jkdkStore) {
		this.jkdkStore = jkdkStore;
	}
	
	@RequestMapping("/review/removeReview.do")
	public String handleRequest(@RequestParam("reviewId") int reviewId, Model model) throws Exception {
		jkdkStore.deleteReview(reviewId);
		model.addAttribute("data", new Message("리뷰가 삭제되었습니다..", "/user/myPage/buy.do"));
		return "thyme/utils/MessagePage";
	}
}
