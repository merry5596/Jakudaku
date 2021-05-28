package passionx3.jkdk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import passionx3.jkdk.domain.Account;
import passionx3.jkdk.service.jkdkFacade;

@Controller
@RequestMapping("/user/deleteAccount.do")
public class DeleteAccountController {
	
	@Autowired
	private jkdkFacade jkdkStore;
	
	@Value("DeleteAccountForm")
	private String formViewName;
	
	@ModelAttribute("DeleteAccountForm")
	public AccountForm formBackingObject(HttpServletRequest request) 
			throws Exception {
		Account userSession = 
			(Account) WebUtils.getSessionAttribute(request, "userSession");
		
			return new AccountForm();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return formViewName;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String deleteAccount(@RequestParam("account.password") String password, 
			 HttpSession session) throws Exception {
		Account curAccount = (Account)session.getAttribute("userSession");
		try {
			System.out.println(curAccount.getPassword() + " " + password + " " + curAccount.getName());
			if (curAccount.getPassword().equals(password)) {
				jkdkStore.removeAccount(curAccount.getUserId());
				session.removeAttribute("userSession");
				session.invalidate();
			} else {	// 패스워드가 일치하지 않음
				return formViewName;
			}
		} catch (Exception ex) {	// db 오류
			ex.printStackTrace();
			return formViewName; 
		}
		
		return "thyme/user/Goodbye";
	}
}
