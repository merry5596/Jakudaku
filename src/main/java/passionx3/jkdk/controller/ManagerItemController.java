package passionx3.jkdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jpetstore.service.PetStoreFacade;

@Controller
public class ManagerItemController {
	private jkdkFacade jkdk;
	@Autowired
	public void setJkdk(jkdkFacade jkdk) {
		this.jkdk = jkdk;
	}
	
	@RequestMapping("/manager/approveItem.do")
	public String approveItem(
			@RequestParam("itemId") int itemId) throws Exception {
		if (jkdk.approveItem(itemId) == 0) {
			System.out.println("ManagerItemController: Item 승인 실패");
		}
		return "redirect:/manager/myPage/fundingItem.do";
	}
	
	@RequestMapping("/manager/refuseItem.do")
	public String refuseItem(
			@RequestParam("itemId") int itemId) throws Exception {
		if (jkdk.refuseItem(itemId) == 0) {
			System.out.println("ManagerItemController: Item 승인 거부 실패");
		}
		return "redirect:/manager/myPage/fundingItem.do";
	}
}
