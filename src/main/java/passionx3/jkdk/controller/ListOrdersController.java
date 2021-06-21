package passionx3.jkdk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import passionx3.jkdk.domain.Account;

@Controller
@SessionAttributes("userSession")
public class ListOrdersController {

	@RequestMapping("/shop/listOrders.do")
	public ModelAndView handleRequest(
		@ModelAttribute("Account") Account account
		) throws Exception {
		return new ModelAndView("ListOrders");
	}

}
