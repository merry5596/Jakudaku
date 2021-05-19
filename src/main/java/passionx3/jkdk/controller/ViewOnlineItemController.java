package passionx3.jkdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import passionx3.jkdk.domain.Online;
import passionx3.jkdk.service.jkdkFacade;

@Controller
public class ViewOnlineItemController {

	private jkdkFacade jkdkStore;
	
	@Autowired
	public void setJkdkStore(jkdkFacade jkdkStore) {
		this.jkdkStore = jkdkStore;
	}
	
	@RequestMapping("item/viewOnlineItem.do")
	public String handleRequest(@RequestParam("itemId") String itemId, ModelMap model) throws Exception {
		Online online = this.jkdkStore.getOnlineItem(itemId);
		model.put("online", online);
		return "/viewOnlineItem";
	}
}
