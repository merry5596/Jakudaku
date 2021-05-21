package passionx3.jkdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import passionx3.jkdk.domain.Account;
import passionx3.jkdk.service.jkdkFacade;

@Controller
@SessionAttributes("userSession")
public class ListOrdersController {

	@Autowired
	private jkdkFacade jkdkStore;

	@RequestMapping("/shop/listOrders.do")
	public ModelAndView handleRequest(
		@ModelAttribute("Account") Account account
		) throws Exception {
		String username = account.getName();
		return new ModelAndView("ListOrders");
				//(orderList, jkdkStore.getOrdersByUsername(username));
	}

}
