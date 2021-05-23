package passionx3.jkdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import passionx3.jkdk.domain.Order;

import passionx3.jkdk.domain.Account;
import passionx3.jkdk.service.jkdkFacade;

@Controller
@SessionAttributes("userSession")
public class ViewOrderController {

	@Autowired
	private jkdkFacade jkdkStore;

	@RequestMapping("/order/viewOrder.do")
	public ModelAndView handleRequest(
			@ModelAttribute("user") Account user,
			@RequestParam("orderId") int orderId
			) throws Exception {
		
		Order order = jkdkStore.getOrderByOrderId(orderId);
		if (user.getUserId().equals(order.getUserId())) {
			return new ModelAndView("ViewOrder", "order", order);
		}
		else {
			return new ModelAndView("Error", "message", "You may only view your own orders.");
		}
	}
}
