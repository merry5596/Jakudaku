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
import passionx3.jkdk.domain.FundOrder;
import passionx3.jkdk.domain.Funding;
import passionx3.jkdk.service.FundOrderValidator;
import passionx3.jkdk.service.jkdkFacade;

@Controller
@SessionAttributes("fundOrderForm")
public class FundOrderController {
	@Autowired
	private jkdkFacade jkdkStore;
	@Autowired
	private FundOrderValidator fundOrderValidator;
	
	@ModelAttribute("fundOrderForm")
	public FundOrderForm createFundOrderForm() {
		return new FundOrderForm(new FundOrder());
	}
//	
//	@ModelAttribute("sessionCart")
//	public Cart createCart(HttpSession session) {
//		Cart cart = (Cart)session.getAttribute("sessionCart");
//		if (cart == null)
//			cart = new Cart();
//		return cart;
//	}
	
	@RequestMapping("/order/newFundOrder.do")
	public ModelAndView initNewFundOrder(HttpServletRequest request,
			@ModelAttribute("fundOrderForm") FundOrderForm fundOrderForm,
			@RequestParam("itemId") String itemId,
			@RequestParam("quantity") int quantity
			) throws ModelAndViewDefiningException {
		Account userSession = (Account) request.getSession().getAttribute("userSession");
		// Re-read account from DB at team's request.
		Account account = jkdkStore.getAccount(userSession.getUserId());
		
		Funding funding = jkdkStore.getFundingItemById(Integer.parseInt(itemId));
		fundOrderForm.getFundOrder().initFundOrder(account, funding, quantity);
		
		System.out.println("contr: " + fundOrderForm.getFundOrder().getLineItem().getLineItemId());
		
		ModelAndView mav = new ModelAndView("thyme/order/NewFundOrder");
		mav.addObject("isValidation", false);
		mav.addObject("account", account);
		return mav;

//		else {
//			ModelAndView modelAndView = new ModelAndView("cart");
//			modelAndView.addObject("message", "An order could not be created because a cart could not be found.");
//			throw new ModelAndViewDefiningException(modelAndView);
//		}
	}
	
	@RequestMapping("/order/newFundOrderSubmitted.do")
	public ModelAndView submitFundOrder(HttpServletRequest request,
			@ModelAttribute("fundOrderForm") FundOrderForm fundOrderForm, 
			BindingResult result, SessionStatus status, HttpSession session) {
		
			// from NewOrderForm
			fundOrderValidator.validate(fundOrderForm.getFundOrder(), result);

			if (result.hasErrors()) {
				Account userSession = (Account) request.getSession().getAttribute("userSession");
				Account account = jkdkStore.getAccount(userSession.getUserId());
				ModelAndView mav = new ModelAndView("thyme/order/NewFundOrder");
				mav.addObject("isValidation", true);
				mav.addObject("account", account);
				return mav;
			}

			int dbResult = jkdkStore.insertFundOrder(fundOrderForm.getFundOrder());
			
			if (dbResult < 1) {
				return new ModelAndView("thyme/order/NewFundOrder");	// message는 아직은...
			}
			
			ModelAndView mav = new ModelAndView("thyme/order/ViewFundOrder");
			mav.addObject("fundOrder", fundOrderForm.getFundOrder());
			mav.addObject("message", "펀딩이 완료되었습니다.");
			status.setComplete();  // remove sessionCart and orderForm from session
			
//			Account userSession = jkdkStore.getAccount(((Account)session.getAttribute("userSession")).getUserId());
//			session.setAttribute("userSession", userSession);
			
			return mav;
	}
	
	// test 후 삭제
	@RequestMapping("/order/test2.do")
	public ModelAndView test(HttpServletRequest request,
			@ModelAttribute("fundOrderForm") FundOrderForm fundOrderForm, 
			BindingResult result, SessionStatus status) {
		
			// from NewOrderForm
			// orderValidator.validateCreditCard(orderForm.getOrder(), result);

			if (result.hasErrors())
				return new ModelAndView("thyme/order/NewFundOrder");

			FundOrder fo = jkdkStore.getFundOrderByOrderId(22);
			
			ModelAndView mav = new ModelAndView("thyme/order/ViewFundOrder");
			mav.addObject("fundOrder", fo);
			System.out.println(fo.getOrderId());
			mav.addObject("message", "펀딩이 완료되었습니다.");
			status.setComplete();  // remove sessionCart and orderForm from session
			return mav;
	}
	
}
