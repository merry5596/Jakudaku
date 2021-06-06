package passionx3.jkdk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import passionx3.jkdk.domain.Order;

import passionx3.jkdk.domain.Account;
import passionx3.jkdk.service.jkdkFacade;

@Controller
public class ViewOrderController {

	@Autowired
	private jkdkFacade jkdkStore;

	@RequestMapping("/order/viewOrder.do")
	public ModelAndView handleRequest(HttpServletRequest request, @RequestParam("orderId") int orderId) throws Exception {

		Order o = jkdkStore.getOrderByOrderId(orderId);

				
		ModelAndView mav = new ModelAndView("thyme/order/ViewOrder");
		mav.addObject("order", o);
		mav.addObject("message", "");
		return mav;
		
	}
}
