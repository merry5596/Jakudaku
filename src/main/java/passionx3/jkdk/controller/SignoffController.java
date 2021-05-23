package passionx3.jkdk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userSession")
public class SignoffController {
	
	@RequestMapping("/user/signoff.do")
	public String handleRequest(HttpSession session) {
		session.removeAttribute("userSession");
		session.invalidate();

		return "redirect:/";
	}
}

