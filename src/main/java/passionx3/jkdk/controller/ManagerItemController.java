package passionx3.jkdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import passionx3.jkdk.service.jkdkFacade;

@Controller
@SessionAttributes("userSession")

public class ManagerItemController {
	private jkdkFacade jkdk;
	@Autowired
	public void setJkdk(jkdkFacade jkdk) {
		this.jkdk = jkdk;
	}
	
	@RequestMapping("/manager/online/approveItem.do")
	public String approveOnlineItem(
			@RequestParam("itemId") int itemId) throws Exception {
		if (jkdk.approveItem(itemId) == 0) {
			System.out.println("ManagerItemController: Item 승인 실패");
		}
		return "redirect:/manager/myPage/onlineItem.do";
	}
	
	@RequestMapping("/manager/online/refuseItem.do")
	public String refuseOnlineItem(
			@RequestParam("itemId") int itemId) throws Exception {
		if (jkdk.refuseItem(itemId) == 0) {
			System.out.println("ManagerItemController: Item 승인 거부 실패");
		}
		return "redirect:/manager/myPage/onlineItem.do";
	}
	
	@RequestMapping("/manager/funding/approveItem.do")
	public String approveFundingItem(
			@RequestParam("itemId") int itemId) throws Exception {
		if (jkdk.approveItem(itemId) == 0) {
			System.out.println("ManagerItemController: Item 승인 실패");
		}
		return "redirect:/manager/myPage/fundingItem.do";
	}
	
	@RequestMapping("/manager/funding/refuseItem.do")
	public String refuseFundingItem(
			@RequestParam("itemId") int itemId) throws Exception {
		if (jkdk.refuseItem(itemId) == 0) {
			System.out.println("ManagerItemController: Item 승인 거부 실패");
		}
		return "redirect:/manager/myPage/fundingItem.do";
	}
}
