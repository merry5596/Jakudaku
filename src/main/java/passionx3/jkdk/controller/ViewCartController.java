package passionx3.jkdk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import passionx3.jkdk.domain.Cart;

@Controller
@SessionAttributes({"sessionCart", "userSession"})
public class ViewCartController { 
	
	@ModelAttribute("sessionCart")
	public Cart createCart(HttpSession session) {
		Cart cart = (Cart)session.getAttribute("sessionCart");
		if (cart == null)
			cart = new Cart();
		return cart;
	}
	
	@RequestMapping("/order/viewCart.do")
	public ModelAndView viewCart(
		 HttpServletRequest request,
		 @RequestParam(value="page", required=false) String page,
		 @ModelAttribute("sessionCart") Cart cart) throws Exception {
		handleRequest(page, cart);
		return new ModelAndView("Cart", "cart", cart);
	}

	private void handleRequest(String page, Cart cart)
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
