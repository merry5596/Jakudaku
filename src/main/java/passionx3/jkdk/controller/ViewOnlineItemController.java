package passionx3.jkdk.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import passionx3.jkdk.domain.Account;
import passionx3.jkdk.domain.Online;
import passionx3.jkdk.domain.Review;
import passionx3.jkdk.domain.UserLike;
import passionx3.jkdk.service.jkdkFacade;

@Controller
public class ViewOnlineItemController {
	private jkdkFacade jkdkStore;
	
	@Autowired
	public void setJkdkStore(jkdkFacade jkdkStore) {
		this.jkdkStore = jkdkStore;
	}
	
	@RequestMapping("/item/viewOnlineItem.do")
	public String handleRequest(@RequestParam("itemId") Integer itemId, ModelMap model, HttpSession session) throws Exception {
		if (itemId != null) {
			Online online = this.jkdkStore.getOnlineItemById(itemId);
			List<Review> reviewList = jkdkStore.getReviewsByItemId(itemId);
			
			model.put("online", online);
			model.put("reviewList", reviewList);
			
			Account user = (Account)session.getAttribute("userSession");
			if (user != null) {
				UserLike userLike = jkdkStore.getUserLike(itemId, user.getUserId());
				model.put("userLike", userLike);
			} 
		}
		return "thyme/item/ViewOnlineItem";
	}
	
	@ResponseBody
	@RequestMapping("/item/toggleLike.json")
	public UserLike toggleLike(@RequestBody UserLike userLike) throws Exception {
		UserLike updateUserLike;
		if (userLike.isClicked()) {
			updateUserLike = jkdkStore.plusLike(userLike);
		} else {
			updateUserLike = jkdkStore.minusLike(userLike);
		}
		
		return updateUserLike;
	}
}
