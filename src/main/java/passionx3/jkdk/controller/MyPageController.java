package passionx3.jkdk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import passionx3.jkdk.domain.Account;

@Controller
@SessionAttributes("userSession")
public class MyPageController {
	
	@RequestMapping("/user/myPage.do")
	public ModelAndView viewMyPagesMain(@SessionAttribute("userSession") Account userSession) {
		if (userSession == null) {
			return new ModelAndView("home", "message", "로그인 후 이용 가능합니다.");
		}
		
		return new ModelAndView("myPage");
	}
	
//	@RequestMapping("/user/myPage/buy.do")
//	public String viewBuyItem() {
//		
//	}
//	
//	@RequestMapping("/user/myPage/sell.do")
//	public String viewSellItem() {
//		
//	}
}
