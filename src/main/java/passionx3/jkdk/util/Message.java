package passionx3.jkdk.util;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;


public class Message {
	String message = "";
	String href = "";
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Message() {

	}
	
	public Message(String message, String href) {
		this.message = message;
		this.href = href;
	}
}