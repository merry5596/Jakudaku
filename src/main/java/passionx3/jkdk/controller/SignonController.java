package passionx3.jkdk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import passionx3.jkdk.domain.Account;
import passionx3.jkdk.service.jkdkFacade;
import passionx3.jkdk.util.Message;

@Controller
@SessionAttributes("userSession")
public class SignonController{
	
	@Autowired
	private jkdkFacade jkdkStore;
	
	@RequestMapping("/user/signon.do")
	public String handleRequest(HttpServletRequest request,
			@RequestParam("userId") String userId,
			@RequestParam("password") String password,
			@RequestParam(value="forwardAction", required=false) String forwardAction,
			Model model) throws Exception {
		Account userSession = jkdkStore.getAccount(userId, password);
		
		if (userSession == null) {
			model.addAttribute("data", new Message(" 가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.", "/"));
			return "thyme/utils/MessagePage";
		}
		
		model.addAttribute("userSession", userSession);
		
		if (forwardAction != null)
			return "redirect:" + forwardAction;
		else 
			return "redirect:/";
	}
}
