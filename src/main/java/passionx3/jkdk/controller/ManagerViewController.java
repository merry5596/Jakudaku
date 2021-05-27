package passionx3.jkdk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import passionx3.jkdk.domain.Funding;
import passionx3.jkdk.domain.Online;
import passionx3.jkdk.service.jkdkFacade;

@Controller
@SessionAttributes("userSession")
public class ManagerViewController {
	
	@Autowired
	private jkdkFacade jkdk;
	

	@RequestMapping("/manager/myPage/onlineItem.do") 	//관리자 마이페이지의 default
	public String viewOnlineItem(ModelMap model) throws Exception {
		List<Online> items = jkdk.getNotApprovedOnlineItems();
		System.out.println(items);
		model.put("items", items);
		
		return "thyme/manager/Manager";
	}
	
	@RequestMapping("/manager/myPage/fundingItem.do")
	public String viewFundingItem(ModelMap model) throws Exception {
		List<Funding> items = jkdk.getNotApprovedFundingItems();
		
		model.put("items", items);
	
		return "thyme/manager/Funding";
	}
}
