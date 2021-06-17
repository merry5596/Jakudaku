package passionx3.jkdk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import passionx3.jkdk.service.jkdkFacade;
import passionx3.jkdk.dao.mybatis.mapper.ItemMapper;
import passionx3.jkdk.dao.mybatis.mapper.LineItemMapper;
import passionx3.jkdk.dao.mybatis.mapper.OnlineMapper;
import passionx3.jkdk.domain.Account;
import passionx3.jkdk.domain.Item;
import passionx3.jkdk.domain.Review;
import passionx3.jkdk.service.ReviewFormValidator;


@Controller
@SessionAttributes("userSession")
@RequestMapping({"/review/writeReview.do", "/review/viewOrders.do"})
public class ReviewFormController {

	@Value("EditReviewForm")
	private String formViewName;
	@Value("redirect:/item/viewOnlineItem.do?itemId=")
	private String onlineViewName;
	
	private jkdkFacade jkdkStore;
	
	@Autowired
	private OnlineMapper onlineMapper;

	
	@Autowired
	public void setJkdkStore(jkdkFacade jkdkStore) {
		this.jkdkStore = jkdkStore;
	}
	
	@Autowired
	private ReviewFormValidator validator;
	public void setValidator(ReviewFormValidator validator) {
		this.validator = validator;
	}
	
	@ModelAttribute("reviewForm") 
	public ReviewForm formBacking(HttpServletRequest request, @RequestParam("lineItemId") int lineItemId) throws Exception {
		
		String reviewId = jkdkStore.getReviewIdByLineItmeId(lineItemId);
		
		if (reviewId != null) { // edit an existing review
			return new ReviewForm(jkdkStore.getReview(Integer.parseInt(reviewId)));
		}
		else { // create a new review
			return new ReviewForm();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm( @RequestParam("lineItemId") int lineItemId, ModelMap model) {
		Item item = onlineMapper.getOnlineItemByLineItemId(lineItemId);
		model.put("item", item);
		
		return formViewName;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request, HttpSession session,
			@ModelAttribute("reviewForm") ReviewForm reviewForm, @RequestParam("lineItemId") int lineItemId, ModelMap model, 
			 BindingResult result) throws Exception {

		Item item = onlineMapper.getOnlineItemByLineItemId(lineItemId);
		model.put("item", item);
		
		validator.validate(reviewForm, result);
		if (result.hasErrors()) return formViewName;

		try {
			if (reviewForm.isNewReview()) {
				Review review = reviewForm.getReview();
				Account account = (Account)session.getAttribute("userSession");
				String userId = account.getUserId();
				
				review.setItemId(item.getItemId());
				review.setUserId(userId);
				review.setLineItemId(lineItemId);
				
				System.out.println(review.getContent() + ", " + review.getItemId() + ", " + review.getUserId());
				jkdkStore.insertReview(review);
			}
			else {
				jkdkStore.updateReview(reviewForm.getReview());
			}
		}
		catch (DataIntegrityViolationException ex) {
			result.rejectValue("review.reviewId", "USER_ID_ALREADY_EXISTS","User ID already exists: choose a different ID."); 
			return formViewName; 
		}
		

		return onlineViewName + item.getItemId();
	}
}
