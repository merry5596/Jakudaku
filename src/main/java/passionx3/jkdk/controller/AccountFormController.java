package passionx3.jkdk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import passionx3.jkdk.domain.Account;
import passionx3.jkdk.service.AccountFormValidator;
import passionx3.jkdk.service.jkdkFacade;

// 등록, 수정 둘 다 이곳에서
@Controller
@RequestMapping({"/user/newAccount.do","/user/editAccount.do"})
public class AccountFormController {

	@Value("EditAccountForm")
	private String formViewName;
	
	@Autowired
	private jkdkFacade jkdkStore;
	
	@Autowired
	private AccountFormValidator validator;
	
	@ModelAttribute("accountForm")
	public AccountForm formBackingObject(HttpServletRequest request) 
			throws Exception {
		Account userSession = 
			(Account) WebUtils.getSessionAttribute(request, "userSession");
		if (userSession != null) {	// edit an existing account
			// Session 방식 변경했지만 여전히 문제... 
			// return new AccountForm(userSession); 하면 안되는 이유는?
			return new AccountForm(jkdkStore.getAccount(userSession.getUserId()));
		}
		else {	// create a new account
			return new AccountForm();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return formViewName;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request, HttpSession session,
			@ModelAttribute("accountForm") AccountForm accountForm,
			BindingResult result) throws Exception  {
		
		validator.validate(accountForm, result);
		if (result.hasErrors()) return formViewName;
		
		try {
			if (accountForm.isNewAccount()) {
				jkdkStore.insertAccount(accountForm.getAccount());
			}
			else {
				jkdkStore.updateAccount(accountForm.getAccount());
			}
		}
		catch (DataIntegrityViolationException ex) {
			result.rejectValue("account.userId", "USER_ID_ALREADY_EXISTS",
					"User ID already exists: choose a different ID.");
			return formViewName; 
		}
		
		Account userSession = jkdkStore.getAccount(accountForm.getAccount().getUserId());
		session.setAttribute("userSession", userSession);
		
		return "redirect:/user/hello.do";
	}
	
	
	
}
