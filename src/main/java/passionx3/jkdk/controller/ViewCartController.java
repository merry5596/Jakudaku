package passionx3.jkdk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import passionx3.jkdk.domain.Account;
import passionx3.jkdk.domain.Cart;

import passionx3.jkdk.service.jkdkFacade;

@Controller
@SessionAttributes("sessionCart")
public class ViewCartController { 
	@Autowired
	private jkdkFacade jkdkStore;
	
	@ModelAttribute("sessionCart")
	public Cart createCart(HttpSession session) {
		Cart cart = (Cart)session.getAttribute("sessionCart");
		if (cart == null) cart = new Cart();
		return cart;
	}
	
	@RequestMapping("/shop/viewCart.do")
	public ModelAndView viewCart(
			HttpServletRequest request,
			@ModelAttribute("sessionCart") Cart cart) 
			throws Exception {
		
		Account account = (Account) WebUtils.getSessionAttribute(request, "user");
		
		return new ModelAndView("Cart", "cart", cart);
	}

	@RequestMapping("/shop/checkout.do")
	public ModelAndView checkout(
			HttpServletRequest request,
			@ModelAttribute("sessionCart") Cart cart) 
			throws Exception {
		Account account = (Account) WebUtils.getSessionAttribute(request, "user");
		return new ModelAndView("Checkout", "cart", cart);
	}
	
}
