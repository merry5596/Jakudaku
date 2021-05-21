
package passionx3.jkdk.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import passionx3.jkdk.domain.Online;
import passionx3.jkdk.domain.Funding;
import passionx3.jkdk.domain.Order;
import passionx3.jkdk.domain.FundOrder;
import passionx3.jkdk.domain.LineItem;
import passionx3.jkdk.service.jkdkFacade;
import passionx3.jkdk.domain.Account;

@Controller
public class MyPageController {
	private jkdkFacade jkdk;
	@Autowired
	public void setJkdk(jkdkFacade jkdk) {
		this.jkdk = jkdk;
	}
  
  @RequestMapping("/user/myPage.do")
	public ModelAndView viewMyPagesMain(@SessionAttribute("userSession") Account userSession) {
		if (userSession == null) {
			return new ModelAndView("thyme/Home", "message", "로그인 후 이용 가능합니다.");
		}
		
		return new ModelAndView("thyme/MyPage");
	}
	
	@RequestMapping("/user/myPage/buy.do")
	public String viewBuyItem(ModelMap model, HttpSession session) throws Exception {
		String userId = (String) session.getAttribute("userId"); 
		
		Map<String, List<LineItem>> lineItemMap= jkdk.getLineItemsByUserId(userId); //dao 인터페이스에 추가해야 할 메소드

		model.put("lineItemMap", lineItemMap);
		
		return "thyme/myPage/buy";
	}
	
	@RequestMapping("/user/myPage/sell.do")
	public String viewSellItem(ModelMap model, HttpSession session) throws Exception {
		String userId = (String) session.getAttribute("userId");
		
		List<Online> onlineList = jkdk.getOnlineItemListByProducerId(userId);
		List<Funding> fundingList = jkdk.getFundingItemListByProducerId(userId);

		model.put("onlineList", onlineList);
		model.put("fundingList", fundingList);
		
		return "thyme/myPage/sell";
	}
}
