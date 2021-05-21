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
import org.springframework.web.servlet.ModelAndViewDefiningException;

import passionx3.jkdk.domain.Account;
import passionx3.jkdk.domain.Cart;
import passionx3.jkdk.domain.Online;
import passionx3.jkdk.domain.Order;
import passionx3.jkdk.service.OrderValidator;
import passionx3.jkdk.service.jkdkFacade;

@Controller
@SessionAttributes({"sessionCart", "orderForm"})
public class OrderController {
	@Autowired
	private jkdkFacade jkdkStore;
	@Autowired
	private OrderValidator orderValidator;
	
	@ModelAttribute("orderForm")
	public OrderForm createOrderForm() {
		return new OrderForm(new Order());
	}
/*
	@ModelAttribute("creditCardTypes")
	public List<String> referenceData() {
		ArrayList<String> creditCardTypes = new ArrayList<String>();
		creditCardTypes.add("Visa");
		creditCardTypes.add("MasterCard");
		creditCardTypes.add("American Express");
		return creditCardTypes;			
	}
	*/
	@RequestMapping("/order/newOrder.do")
	public String initNewOrder(HttpServletRequest request,
			@ModelAttribute("sessionCart") Cart cart,
			@ModelAttribute("orderForm") OrderForm orderForm,
			@RequestParam(value = "itemId", required=false) String itemId
			) throws ModelAndViewDefiningException {
		Account userSession = (Account) request.getSession().getAttribute("userSession");
		// Re-read account from DB at team's request.
		Account account = jkdkStore.getAccount(userSession.getName());
		
		if (itemId != null) {	// 개별 구매
			Online item = (Online) jkdkStore.getItem(itemId);
			Cart tempCart = new Cart();
			tempCart.addItem(item);
			orderForm.getOrder().initOrder(account, tempCart);
			return "NewOrderForm";	
		}
		else if (cart != null) {	// 카트 전체 구매
			orderForm.getOrder().initOrder(account, cart);
			return "NewOrderForm";	
		}
		else {
			ModelAndView modelAndView = new ModelAndView("cart");
			modelAndView.addObject("message", "An order could not be created because a cart could not be found.");
			throw new ModelAndViewDefiningException(modelAndView);
		}
	}
	
	@RequestMapping("/order/newOrderSubmitted.do")
	public ModelAndView submitOrder(HttpServletRequest request,
			@ModelAttribute("orderForm") OrderForm orderForm, 
			BindingResult result, SessionStatus status) {
			// from NewOrderForm
			orderValidator.validateCreditCard(orderForm.getOrder(), result);
			if (result.hasErrors())
				return new ModelAndView("thyme/order/NewOrderForm");
			
			jkdkStore.insertOrder(orderForm.getOrder());
			ModelAndView mav = new ModelAndView("thyme/order/ViewOrder");
			mav.addObject("order", orderForm.getOrder());
			mav.addObject("message", "Thank you, your order has been submitted.");
			status.setComplete();  // remove sessionCart and orderForm from session
			return mav;
	}
	
}
