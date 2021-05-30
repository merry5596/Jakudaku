package passionx3.jkdk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import passionx3.jkdk.domain.Online;
import passionx3.jkdk.domain.Review;
import passionx3.jkdk.service.jkdkFacade;

@Controller
public class ViewOnlineItemController {
	private jkdkFacade jkdkStore;
	
	@Autowired
	public void setJkdkStore(jkdkFacade jkdkStore) {
		this.jkdkStore = jkdkStore;
	}
	
	@RequestMapping("item/viewOnlineItem.do")
	public String handleRequest(@RequestParam("itemId") Integer itemId, ModelMap model) throws Exception {
		if (itemId != null) {
			Online online = this.jkdkStore.getOnlineItemById(itemId);
			List<Review> reviewList = jkdkStore.getReviewsByItemId(itemId);
			
			model.put("online", online);
			model.put("reviewList", reviewList);
		}
		return "thyme/item/ViewOnlineItem";
		
	}
}
