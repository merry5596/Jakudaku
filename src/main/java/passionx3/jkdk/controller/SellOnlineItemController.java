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

import passionx3.jkdk.service.SellOnlineItemFormValidator;
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
			@Valid @ModelAttribute("sellOnlineForm") SellOnlineForm sellOnlineForm,
			BindingResult result, Model model, 
			@RequestParam("thumbnail1") MultipartFile[] thumbnail, 
			@RequestParam("pcFile") MultipartFile pcFile, 
			@RequestParam("phoneFile") MultipartFile phoneFile, 
			@RequestParam("tabletFile") MultipartFile tabletFile) throws Exception {
		
//		validator.validate(sellOnlineForm, result);
		if (result.hasErrors()) return formViewName;
		
		try {
			if (sellOnlineForm.isNewOnline()) {
				Online online = sellOnlineForm.getOnline();
				Account account = (Account)session.getAttribute("userSession");
				online.setProducerId(account.getUserId());
				
				online = fileUtils.uploadFiles(thumbnail, online);
				online = fileUtils.uploadFiles(pcFile, online);
				online = fileUtils.uploadFiles(phoneFile, online);
				online = fileUtils.uploadFiles(tabletFile, online);

				jkdk.registerOnlineItem(online);
			}
			else {
				Online online = sellOnlineForm.getOnline();
				online = fileUtils.uploadFiles(thumbnail, online);
				online = fileUtils.uploadFiles(pcFile, online);
				online = fileUtils.uploadFiles(phoneFile, online);
				online = fileUtils.uploadFiles(tabletFile, online);
				if (jkdk.updateOnlineItem(online) != 1)
					throw new Exception(); 
			}
		} catch (Exception ex) { //추후에 수정
			System.out.println(ex.getMessage());
//			result.rejectValue("account.username", "ONLINEITEM_SELL_FAIL",
//					"처리하지 못했습니다. 다시 시도해주세요.");
			return formViewName; 
		}
				
		return "redirect:/user/myPage/sell.do"; 
	
	


		
//		DAOFactory daoFactory = new DAOFactory();
//		PostDAO postDAO = daoFactory.getPostDAO();
//
//		String userID = (String) request.getSession().getAttribute("userID");
//
//		// POST: 게시물 등록 처리
//		int rating = -1;
//		int isPrivate = -1;
//		
//		String location = null;
//		String sRating = null;
//		String content = null;
//		String sIsPrivate = null;
//		String photoAddr = null;
//		String tags = null;
//		String filename = null;  
//		
//		boolean check = ServletFileUpload.isMultipartContent(request);
//		//전송된 데이터의 인코드 타입이 multipart 인지 여부를 체크한다.
//		//만약 multipart가 아니라면 파일 전송을 처리하지 않는다.
//		
//		if(check) {//파일 전송이 포함된 상태가 맞다면
//			
//			// 아래와 같이 하면 Tomcat 내부에 복사된 프로젝트의 폴더 밑에 upload 폴더가 생성됨 
//			ServletContext context = request.getServletContext();
//			String path = context.getRealPath("/upload");
//			File dir = new File(path);
//			
//			// Tomcat 외부의 폴더에 저장하려면 아래와 같이 절대 경로로 폴더 이름을 지정함
//			// File dir = new File("C:/Temp");
//			
//			if(!dir.exists()) dir.mkdir();
//			//전송된 파일을 저장할 실제 경로를 만든다.
//			
//			try {
//				DiskFileItemFactory factory = new DiskFileItemFactory();
//                //파일 전송에 대한 기본적인 설정 Factory 클래스를 생성한다.
//                factory.setSizeThreshold(10 * 1024);
//                //메모리에 한번에 저장할 데이터의 크기를 설정한다.
//                //10kb 씩 메모리에 데이터를 읽어 들인다.
//                factory.setRepository(dir);
//                //전송된 데이터의 내용을 저장할 임시 폴더를 지정한다.                
//    
//                ServletFileUpload upload = new ServletFileUpload(factory);
//                //Factory 클래스를 통해 실제 업로드 되는 내용을 처리할 객체를 선언한다.
//                upload.setSizeMax(10 * 1024 * 1024);
//                //업로드 될 파일의 최대 용량을 10MB까지 전송 허용한다.
//                upload.setHeaderEncoding("utf-8");
//                //업로드 되는 내용의 인코딩을 설정한다.
//                                
//                List<FileItem> items = (List<FileItem>)upload.parseRequest(new ServletRequestContext(request));
//                upload.parseParameterMap(request);
//                
//                //upload 객체에 전송되어 온 모든 데이터를 Collection 객체에 담는다.
//                for(int i = 0; i < items.size(); ++i) {
//                	FileItem item = (FileItem)items.get(i);
//                	//commons-fileupload를 사용하여 전송받으면 
//                	//모든 parameter는 FileItem 클래스에 하나씩 저장된다.
//                	
//                	String value = item.getString("utf-8");
//                	//넘어온 값에 대한 한글 처리를 한다.
//                
//                	if(item.isFormField()) {//일반 폼 데이터라면...                		
//                		if(item.getFieldName().equals("location")) location = value;
//                		else if(item.getFieldName().equals("rating")) {
//                			sRating = value;
//                			if (sRating != null)
//                				rating = Integer.parseInt(sRating);
//                		}
//                		else if(item.getFieldName().equals("content")) content = value;
//                		else if(item.getFieldName().equals("is_private")) {
//                			sIsPrivate = value;
//                			if (sIsPrivate != null)
//                				isPrivate = Integer.parseInt(sIsPrivate);
//                		}
//                		else if(item.getFieldName().equals("tags")) tags = value;
//                	}
//                	else {//파일이라면...
//                		if(item.getFieldName().equals("photo")) {
//                		//key 값이 picture이면 파일 저장을 한다.
//                			filename = item.getName();//파일 이름 획득 (자동 한글 처리 됨)
//                			if(filename == null || filename.trim().length() == 0) continue;
//                			//파일이 전송되어 오지 않았다면 건너 뛴다.
//                			filename = filename.substring(filename.lastIndexOf("\\") + 1);
//                			//파일 이름이 파일의 전체 경로까지 포함하기 때문에 이름 부분만 추출해야 한다.
//                			//실제 C:\Web_Java\aaa.gif라고 하면 aaa.gif만 추출하기 위한 코드이다.
//                			File file = new File(dir, filename);
//                			item.write(file);
//                			//파일을 upload 경로에 실제로 저장한다.
//                			//FileItem 객체를 통해 바로 출력 저장할 수 있다.
//                		}
//                	}
//                }
//                
//    			PostDTO post = new PostDTO(location, rating, null, content, isPrivate, filename, userID, null, null);
//
//                
//			}catch(SizeLimitExceededException e) {
//			//업로드 되는 파일의 크기가 지정된 최대 크기를 초과할 때 발생하는 예외처리
//				e.printStackTrace();           
//            }catch(FileUploadException e) {
//            //파일 업로드와 관련되어 발생할 수 있는 예외 처리
//                e.printStackTrace();
//            }catch(Exception e) {            
//                e.printStackTrace();
//            }
//		}	
//
//		return "redirect:/mission/print";
//	}
	}
	
	
	
}
