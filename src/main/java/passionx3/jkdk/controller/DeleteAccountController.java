package passionx3.jkdk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import passionx3.jkdk.domain.Account;
import passionx3.jkdk.service.jkdkFacade;

@Controller
@SessionAttributes("userSession")
@RequestMapping("/user/deleteAccount.do")
public class DeleteAccountController {
	
	@Autowired
	private jkdkFacade jkdkStore;
	
	@Value("thyme/DeleteAccountForm")
	private String formViewName;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return formViewName;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView deleteAccount(@RequestParam("password") String password, 
			@ModelAttribute("userSession") Account userSession) throws Exception {
		Account curAccount = userSession;
		try {
			if (curAccount.getPassword() == password)
				jkdkStore.removeAccount(curAccount.getUserId());
			else {	// 패스워드가 일치하지 않음
				return new ModelAndView(formViewName, "message", "패스워드가 잘못되었습니다.");
			}
		} catch (Exception ex) {	// db 오류
			ex.printStackTrace();
			return new ModelAndView(formViewName); 
		}
		
		return new ModelAndView("redirect:/user/goodbye.do");
	}
}
