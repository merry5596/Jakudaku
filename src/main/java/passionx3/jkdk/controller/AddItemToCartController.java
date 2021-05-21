package passionx3.jkdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import passionx3.jkdk.domain.Cart;
import passionx3.jkdk.domain.Online;
import passionx3.jkdk.service.jkdkFacade;

@Controller
@SessionAttributes("sessionCart")
public class AddItemToCartController { 
	@Autowired
	private jkdkFacade jkdkStore;

	@ModelAttribute("sessionCart")
	public Cart createCart() {
		return new Cart();
	}
	
	@RequestMapping("/shop/addItemToCart.do")
	public ModelAndView handleRequest(
			@RequestParam("workingItemId") String workingItemId,
			@ModelAttribute("sessionCart") Cart cart 
			) throws Exception {
		int itemId = Integer.parseInt(workingItemId);
		if (cart.containsItemId(itemId)) {
			// 처리
			// UI에 "이미 담겼음" 출력
		}
		else {
			// isInStock is a "real-time" property that must be updated
			// every time an item is added to the cart, even if other
			// item details are cached.
			Online item = this.jkdkStore.getOnlineItemById(itemId);
			cart.addItem(item);
		}
		return new ModelAndView("Cart", "cart", cart);
	}
}