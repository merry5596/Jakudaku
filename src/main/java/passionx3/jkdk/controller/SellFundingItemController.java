package passionx3.jkdk.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import passionx3.jkdk.service.jkdkFacade;
import passionx3.jkdk.cotroller.SellFundingForm;
import passionx3.jkdk.domain.Category;
import passionx3.jkdk.domain.Theme;

@Controller
public class SellFundingItemController {
	@Value("sellFundingItemForm")
	private String formViewName;
		
	private jkdkFacade jkdk;
	@Autowired
	public void setJkdk(jkdkFacade jkdk) {
		this.jkdk = jkdk;
	}
	
	@Autowired
	private SellFundingItemFormValidator validator;
	public void setValidator(SellFundingItemFormValidator validator) {
		this.validator = validator;
	}
	
	@ModelAttribute("sellFundingForm")
	public SellFundingForm formBackingObject(HttpServletRequest request) throws Exception {
//		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String itemId = request.getParameter("itemId");
		
		if (itemId != null) {
			return new SellFundingForm(jkdk.getFundingItemById(Integer.parseInt(itemId)));
		} else { 
			return new SellFundingForm();
		}
	}
	
	@ModelAttribute("themeList")
	public List<Theme> getThemeList() { // accessor method
		return jkdk.getAllThemes(); // view에 전달됨
	}
	
	@ModelAttribute("categoryList")
	public List<Category> getCategoryList() { // accessor method
		return jkdk.getAllCategories(); // view에 전달됨
	}
	
	@GetMapping("/item/sellFundingItem.do")
	public String showForm() {
		return formViewName;
	}

	@PostMapping("/item/sellFundingItem.do") 
	public String onSubmit(
			HttpServletRequest request, HttpSession session,
			@ModelAttribute("sellFundingForm") SellFundingForm sellFundingForm,
			BindingResult result) throws Exception {
		validator.validate(sellFundingForm, result);
		
		if (result.hasErrors()) return formViewName;
		try {
			if (sellFundingForm.isNewFunding()) {
				jkdk.registerFundingItem(sellFundingForm.getFunding());
			}
			else {
				jkdk.updateFundingItem(sellFundingForm.getFunding()); //request 흐름도에 상품 수정페이지 없음
			}
		}
		catch (Exception ex) { //추후에 수정
			result.rejectValue("account.username", "FUNDINGITEM_SELL_FAIL",
					"처리하지 못했습니다. 다시 시도해주세요.");
			return formViewName; 
		}
		
		return "redirect:/user/myPage/sell.do";
	}
}
