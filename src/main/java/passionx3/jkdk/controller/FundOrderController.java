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
				
		ModelAndView mav = new ModelAndView("thyme/order/NewFundOrder");
		mav.addObject("isValidationOfAddress", false);
		mav.addObject("account", account);
		return mav;
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
				mav.addObject("account", account);
				return mav;
			}

			int dbResult = jkdkStore.insertFundOrder(fundOrderForm.getFundOrder());
			
			if (dbResult < 1) {
				return new ModelAndView("thyme/order/NewFundOrder");
			}
			
			ModelAndView mav = new ModelAndView("thyme/order/ViewFundOrder");
			mav.addObject("fundOrder", fundOrderForm.getFundOrder());
			mav.addObject("message", "펀딩이 완료되었습니다.");
			status.setComplete();  // remove sessionCart and orderForm from session
			
			return mav;
	}
	
	
}
