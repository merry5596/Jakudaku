package passionx3.jkdk.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import passionx3.jkdk.service.jkdkFacade;
import passionx3.jkdk.service.ReviewFormValidator;
import passionx3.jkdk.domain.Review;


@Controller
@RequestMapping({"/review/writeReview.do", "/review/viewOrders.do"})
public class ReviewFormController {

	@Value("EditReviewForm")
	private String formViewName;
	@Value("redirect:/shop/viewOnlineItem.do")
	private String successViewName;
	
	private jkdkFacade jkdkStore;
	
	@Autowired
	public void setJkdkStore(jkdkFacade jkdkStore) {
		this.jkdkStore = jkdkStore;
	}
	
	@Autowired
	private ReviewFormValidator validator;
	public void setValidator(ReviewFormValidator validator) {
		this.validator = validator;
	}
	
	@ModelAttribute("reviewForm") // command ��ü�� �̸� ����
	public ReviewForm formBacking(HttpServletRequest request, ReviewForm reviewForm) throws Exception {
		
		if (reviewForm.isNewReview() == false) { // edit an existing review
			return new ReviewForm(jkdkStore.getReview(reviewId));
		}
		else { // create a new review
			return new ReviewForm();
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return formViewName;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(HttpServletRequest request, HttpSession session,
			@ModelAttribute("reviewForm") ReviewForm reviewForm,
			BindingResult result) throws Exception {

		validator.validate(reviewForm, result);
		if (result.hasErrors()) return formViewName;

		try {
			if (reviewForm.isNewReview()) {
				jkdkStore.insertReview(reviewForm.getReview());
			}
			else {
				jkdkStore.updateReview(reviewForm.getReview());
			}
		}
		catch (DataIntegrityViolationException ex) {
			result.rejectValue(); 
			return formViewName; 
		}
		
		return successViewName; 
}
