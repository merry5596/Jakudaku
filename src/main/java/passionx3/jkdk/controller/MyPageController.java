
package passionx3.jkdk.controller;

import java.io.File;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import passionx3.jkdk.domain.Online;
import passionx3.jkdk.domain.Order;
import passionx3.jkdk.domain.Funding;
import passionx3.jkdk.domain.LineItem;
import passionx3.jkdk.service.jkdkFacade;
import passionx3.jkdk.domain.Account;
import passionx3.jkdk.domain.AttachDTO;

@Controller
@SessionAttributes("userSession")
public class MyPageController {
	private jkdkFacade jkdk;
	@Autowired
	public void setJkdk(jkdkFacade jkdk) {
		this.jkdk = jkdk;
	}
  
	@RequestMapping("/user/myPage.do")
	public ModelAndView viewMyPagesMain(@SessionAttribute("userSession") Account userSession) {
		if (userSession == null) {
			return new ModelAndView("thyme/Home", "message", "로그인 후 이용 가능합니다.");
		}
		
		return new ModelAndView("thyme/user/MyPage");
	}
	
	@RequestMapping("/user/myPage/buy.do")
	public String viewBuyItem(ModelMap model, HttpSession session) throws Exception {
		Account account = (Account)session.getAttribute("userSession");
		
		String userId = account.getUserId();
		
		Map<String, List<Order>> orderMap = jkdk.getLineItemsByUserId(userId); //dao 인터페이스에 추가해야 할 메소드

		model.put("orderMap", orderMap);
		
		return "/buy";
	}
	
//	@GetMapping("/item/download.do")
//	public void downloadAttachFile(@RequestParam(value = "itemId", required = false) final Long itemId, Model model, HttpServletResponse response) {
//
//		if (itemId == null) throw new RuntimeException("올바르지 않은 접근입니다.");
//
//		AttachDTO fileInfo = boardService.getAttachDetail(itemId);
//		if (fileInfo == null || "Y".equals(fileInfo.getDeleteYn())) {
//			throw new RuntimeException("파일 정보를 찾을 수 없습니다.");
//		}
//
//		String uploadDate = fileInfo.getInsertTime().format(DateTimeFormatter.ofPattern("yyMMdd"));
//		String uploadPath = Paths.get("C:", "jkdk", "upload", uploadDate).toString();
//
//		String filename = fileInfo.getOriginalName();
//		File file = new File(uploadPath, fileInfo.getSaveName());
//
//		try {
//			byte[] data = FileUtils.readFileToByteArray(file);
//			response.setContentType("application/octet-stream");
//			response.setContentLength(data.length);
//			response.setHeader("Content-Transfer-Encoding", "binary");
//			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");
//
//			response.getOutputStream().write(data);
//			response.getOutputStream().flush();
//			response.getOutputStream().close();
//		} catch (IOException e) {
//			throw new RuntimeException("파일 다운로드에 실패하였습니다.");
//		} catch (Exception e) {
//			throw new RuntimeException("시스템에 문제가 발생하였습니다.");
//		}
//	}
	
	@RequestMapping("/user/myPage/sell.do")
	public String viewSellItem(ModelMap model, HttpSession session) throws Exception {
		Account account = (Account)session.getAttribute("userSession");
		
		String userId = account.getUserId();
		
		List<Online> onlineList = jkdk.getOnlineItemListByProducerId(userId);
		List<Funding> fundingList = jkdk.getFundingItemListByProducerId(userId);

		model.put("onlineList", onlineList);
		model.put("fundingList", fundingList);
		
		return "/sell";
	}
}
