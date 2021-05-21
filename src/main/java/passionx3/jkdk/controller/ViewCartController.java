package passionx3.jkdk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import passionx3.jkdk.domain.Account;
import passionx3.jkdk.domain.Cart;

@Controller
@SessionAttributes("sessionCart")
public class ViewCartController { 
	
	@ModelAttribute("sessionCart")
	public Cart createCart(HttpSession session) {
		Cart cart = (Cart)session.getAttribute("sessionCart");
		if (cart == null) cart = new Cart();
		return cart;
	}
	
	@RequestMapping("/shop/viewCart.do")
	public ModelAndView viewCart(
		 HttpServletRequest request,
		 @RequestParam(value="page", required=false) String page,
		 @ModelAttribute("sessionCart") Cart cart) throws Exception {
		Account userSession = (Account) WebUtils.getSessionAttribute(request, "userSession");
		handleRequest(page, cart, userSession);
		return new ModelAndView("Cart", "cart", cart);
	}

	private void handleRequest(String page, Cart cart, Account userSession)
		 throws Exception {
		if ("nextCart".equals(page)) {
			cart.getCartItemList().nextPage();
		}
		else if ("previousCart".equals(page)) {
			cart.getCartItemList().previousPage();
		}
	}
	
/*
	@RequestMapping("/order/checkout.do")
	public ModelAndView checkout(
			HttpServletRequest request,
			@ModelAttribute("sessionCart") Cart cart) 
			throws Exception {
		Account userSession = (Account) WebUtils.getSessionAttribute(request, "userSession");
		return new ModelAndView("Checkout", "cart", cart);
	}
	*/
	
}
