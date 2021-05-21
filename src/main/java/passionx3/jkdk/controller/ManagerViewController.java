package passionx3.jkdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import passionx3.jkdk.domain.Item;
import passionx3.jkdk.service.jkdkFacade;

@Controller
public class ManagerViewController {
	private jkdkFacade jkdk;
	@Autowired
	public void setJkdk(jkdkFacade jkdk) {
		this.jkdk = jkdk;
	}

	@RequestMapping("/manager/myPage/onlineItem.do") 	//관리자 마이페이지의 default
	public String viewOnlineItem(ModelMap model) throws Exception {
		Item item = jkdk.getNotApprovedOnlineItems();
		
		model.put("item", item);
		
		return "/manager/myPage/online";
	}
	
	@RequestMapping("/manager/myPage/fundingItem.do")
	public String viewFundingItem(ModelMap model) throws Exception {
		Item item = jkdk.getNotApprovedFundingItems();
		
		model.put("item", item);
		
		return "/manager/myPage/funding";
	}
}
