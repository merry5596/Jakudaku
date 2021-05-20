package passionx3.jkdk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userSession")
@RequestMapping("/user/signoff.do")
public class SignoffController {
	
	public String handleRequest(HttpServletRequest request, HttpSession session) {
		session.removeAttribute("userSession");
		session.invalidate();
		return "home";
	}
}

