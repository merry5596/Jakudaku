
package passionx3.jkdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import passionx3.jkdk.service.jkdkFacade;

@Controller
@SessionAttributes("userSession")
public class SearchItemsController {
	private jkdkFacade jkdk;
	@Autowired
	public void setJkdk(jkdkFacade jkdk) {
		this.jkdk = jkdk;
	}

	@RequestMapping("/item/searchItems.do")
	public String handleRequest(ModelMap model, @RequestParam("keyword") String keyword) throws Exception {
		model.put("onlineList", jkdk.getOnlineItemsByKeyword(keyword));
		model.put("fundingList", jkdk.getFundingItemsByKeyword(keyword));
		
		return "thyme/item/searchItems";
	} 
}

