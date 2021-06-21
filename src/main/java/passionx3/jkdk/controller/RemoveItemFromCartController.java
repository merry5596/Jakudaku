package passionx3.jkdk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import passionx3.jkdk.domain.Cart;


@Controller
@SessionAttributes("sessionCart")
public class RemoveItemFromCartController { 

	@RequestMapping("/order/removeItemFromCart.do")
	public ModelAndView handleRequest(
			@RequestParam(value = "workingItemId", required = false) String workingItemId,
			@ModelAttribute("sessionCart") Cart cart
		) throws Exception {
		// 전체 삭제
		if (workingItemId == null) {
			cart.removeAllItems();
		}
		// 단일 상품 삭제
		else {
			cart.removeItemById(Integer.parseInt(workingItemId));
		}
		return new ModelAndView("Cart", "cart", cart);
	}
}
