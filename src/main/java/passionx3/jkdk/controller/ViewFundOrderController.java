package passionx3.jkdk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import passionx3.jkdk.domain.FundOrder;
import passionx3.jkdk.domain.Order;
import passionx3.jkdk.service.jkdkFacade;

@Controller
public class ViewFundOrderController {
	@Autowired
	private jkdkFacade jkdkStore;

	@RequestMapping("/order/viewFundOrder.do")
	public ModelAndView handleRequest(HttpServletRequest request, @RequestParam("orderId") int orderId) throws Exception {

		FundOrder o = jkdkStore.getFundOrderByOrderId(orderId);
				
		ModelAndView mav = new ModelAndView("thyme/order/ViewFundOrder");
		mav.addObject("fundOrder", o);
		mav.addObject("message", "");
		return mav;
		
	}

}
