package passionx3.jkdk.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import passionx3.jkdk.service.SellOnlineItemFormValidator;
import passionx3.jkdk.service.jkdkFacade;
import passionx3.jkdk.domain.Category;
import passionx3.jkdk.domain.Theme;

@Controller
@SessionAttributes("userSession")
public class SellOnlineItemController {
	@Value("sellOnlineItemForm")
	private String formViewName;
	
	private jkdkFacade jkdk;
	@Autowired
	public void setJkdk(jkdkFacade jkdk) {
		this.jkdk = jkdk;
	}
	
	@Autowired
	private SellOnlineItemFormValidator validator;
	public void setValidator(SellOnlineItemFormValidator validator) {
		this.validator = validator;
	}
	
	@ModelAttribute("sellOnlineForm")
	public SellOnlineForm formBackingObject(HttpServletRequest request) throws Exception { // accessor method
		String itemId = request.getParameter("itemId");
		
		if (itemId != null) {
			return new SellOnlineForm(jkdk.getOnlineItemById(Integer.parseInt(itemId)));
		} else { 
			return new SellOnlineForm();
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
	
	@GetMapping("/item/sellOnlineItem.do")
	public String showForm(ModelMap model) throws Exception {
		return formViewName;
	}
	
	@PostMapping("/item/sellOnlineItem.do") 
	public String onSubmit(
			HttpServletRequest request, HttpSession session,
			@ModelAttribute("sellOnlineForm") SellOnlineForm sellOnlineForm,
			BindingResult result) throws Exception {
		validator.validate(sellOnlineForm, result);
		
		if (result.hasErrors()) return formViewName;
		try {
			if (sellOnlineForm.isNewOnline()) {
				jkdk.registerOnlineItem(sellOnlineForm.getOnline());
			}
			else {
				jkdk.updateOnlineItem(sellOnlineForm.getOnline()); 
			}
		}
		catch (Exception ex) { //추후에 수정
			result.rejectValue("account.username", "ONLINEITEM_SELL_FAIL",
					"처리하지 못했습니다. 다시 시도해주세요.");
			return formViewName; 
		}
		
		return "redirect:/user/myPage.do"; 
	}
}
