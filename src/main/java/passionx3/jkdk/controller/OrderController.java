package passionx3.jkdk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	@ModelAttribute("sessionCart")
	public Cart createCart(HttpSession session) {
		Cart cart = (Cart)session.getAttribute("sessionCart");
		if (cart == null)
			cart = new Cart();
		return cart;
	}
	
	@RequestMapping("/order/newOrder.do")
	public String initNewOrder(HttpServletRequest request,
			@ModelAttribute("sessionCart") Cart cart,
			@ModelAttribute("orderForm") OrderForm orderForm,
			@RequestParam(value = "itemId", required=false) String itemId
			) throws ModelAndViewDefiningException {
		Account userSession = (Account) request.getSession().getAttribute("userSession");
		// Re-read account from DB at team's request.
		Account account = jkdkStore.getAccount(userSession.getUserId());	//변경함
		
		if (itemId != null) {	// 개별 구매
			Online item = (Online) jkdkStore.getOnlineItemById(Integer.parseInt(itemId));
			Cart tempCart = new Cart();
			tempCart.addItem(item);
			orderForm.getOrder().initOrder(account, tempCart);
			return "thyme/order/NewOrder";	
		}
		else if (cart != null) {	// 카트 전체 구매
			orderForm.getOrder().initOrder(account, cart);
			return "thyme/order/NewOrder";	
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
			BindingResult result, SessionStatus status, HttpSession session) {
		
			if (orderForm.getOrder().getUsedMileage() == orderForm.getOrder().getTotalPrice()) {	// 적립금으로만 구매
				orderForm.getOrder().removePaymentMethod();
			}
		
			// from NewOrderForm
			orderValidator.validateCreditCard(orderForm.getOrder(), result);
			
			if (result.hasErrors()) {
				System.out.println(result.toString());
				return new ModelAndView("thyme/order/NewOrder");
			}
			
			int dbResult = jkdkStore.insertOrder(orderForm.getOrder());
			
			if (dbResult < 1) {
				return new ModelAndView("thyme/order/NewOrder");	// message는 아직은...
			}
			
			ModelAndView mav = new ModelAndView("thyme/order/ViewOrder");
			mav.addObject("order", orderForm.getOrder());
			mav.addObject("message", "주문이 완료되었습니다.");
			status.setComplete();  // remove sessionCart and orderForm from session
			
			Account userSession = jkdkStore.getAccount(((Account)session.getAttribute("userSession")).getUserId());
			session.setAttribute("userSession", userSession);
			
			return mav;
	}

	// test 후 삭제
	@RequestMapping("/order/test.do")
	public ModelAndView test(HttpServletRequest request,
			@ModelAttribute("orderForm") OrderForm orderForm, 
			BindingResult result, SessionStatus status) {
		
			// from NewOrderForm
			// orderValidator.validateCreditCard(orderForm.getOrder(), result);

			if (result.hasErrors())
				return new ModelAndView("thyme/order/NewOrder");

			Order o = jkdkStore.getOrderByOrderId(-2);
			
			System.out.println(o.getLineItems().get(0).getUnitPrice());
			
			ModelAndView mav = new ModelAndView("thyme/order/ViewOrder");
			mav.addObject("order", o);
			System.out.println(o.getOrderId());
			mav.addObject("message", "주문이 완료되었습니다.");
			status.setComplete();  // remove sessionCart and orderForm from session
			return mav;
	}
	
}
