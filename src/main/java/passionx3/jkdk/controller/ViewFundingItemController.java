package passionx3.jkdk.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import passionx3.jkdk.domain.Account;
import passionx3.jkdk.domain.Funding;
import passionx3.jkdk.domain.UserLike;
import passionx3.jkdk.service.jkdkFacade;

@Controller
public class ViewFundingItemController {
	
	private jkdkFacade jkdkStore;
	
	@Autowired
	public void setJkdkStore(jkdkFacade jkdkStore) {
		this.jkdkStore = jkdkStore;
	}
	
	@RequestMapping("/item/viewFundingItem.do")
	public String handleRequest(@RequestParam("itemId") int itemId, ModelMap model, HttpSession session) throws Exception {
		Funding funding = this.jkdkStore.getFundingItemById(itemId);
		model.put("funding", funding);
		
		Account user = (Account)session.getAttribute("userSession");
		if (user != null) {
			UserLike userLike = jkdkStore.getUserLike(itemId, user.getUserId());
			model.put("userLike", userLike);
		} 
		
		return "thyme/item/ViewFundingItem";
	}
}
