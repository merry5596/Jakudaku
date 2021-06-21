package passionx3.jkdk.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import passionx3.jkdk.service.jkdkFacade;
import passionx3.jkdk.util.FileUtils;
import passionx3.jkdk.domain.Account;
import passionx3.jkdk.domain.Category;
import passionx3.jkdk.domain.Online;
import passionx3.jkdk.domain.Theme;

@Controller
public class SellOnlineItemController {
	@Value("thyme/item/SellOnlineItemForm")
	private String formViewName;
	
	private jkdkFacade jkdk;
	@Autowired
	public void setJkdk(jkdkFacade jkdk) {
		this.jkdk = jkdk;
	}
	
	@Autowired
	private FileUtils fileUtils;
	
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
			@Valid @ModelAttribute("sellOnlineForm") SellOnlineForm sellOnlineForm,
			BindingResult result, Model model, 
			@RequestParam(value="thumbnail1", required=false) MultipartFile[] thumbnail, 
			@RequestParam(value="pcFile", required=false) MultipartFile pcFile, 
			@RequestParam(value="phoneFile", required=false) MultipartFile phoneFile, 
			@RequestParam(value="tabletFile", required=false) MultipartFile tabletFile) throws Exception {
		
		if (result.hasErrors()) return formViewName;
		
		try {
			if (sellOnlineForm.isNewOnline()) {
				Online online = sellOnlineForm.getOnline();
				Account account = (Account)session.getAttribute("userSession");
				online.setProducerId(account.getUserId());
				online.setProducerName(account.getAlias());

				online = (Online) fileUtils.uploadFiles(thumbnail, online);
				online = fileUtils.uploadFiles(pcFile, online);
				online = fileUtils.uploadFiles(phoneFile, online);
				online = fileUtils.uploadFiles(tabletFile, online);
				
				jkdk.setWaterMark(online);
			
				jkdk.registerOnlineItem(online);
			}
			else {
				Online online = sellOnlineForm.getOnline();
				
				if (jkdk.updateOnlineItem(online) != 1)
					throw new Exception(); 
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return formViewName; 
		}
				
		return "redirect:/user/myPage/sell.do"; 
	}
	
	
	
}
