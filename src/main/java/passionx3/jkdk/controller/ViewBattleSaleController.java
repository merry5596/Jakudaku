package passionx3.jkdk.controller;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import passionx3.jkdk.domain.BattleSale;
import passionx3.jkdk.service.jkdkFacade;

@Controller
public class ViewBattleSaleController { 
	@Autowired
	private jkdkFacade jkdkStore;
	
	@RequestMapping("/battlesale/viewBattleSale.do")
	public String handleRequest(ModelMap model) throws Exception {
		
		BattleSale battleSale = jkdkStore.getBattleSale();

		//Item item = itemDao.getItem(timeSale.getItemId());
		
		model.put("battleSale", battleSale);
		//model.put("item1", item1);
		//model.put("item2", item2);
		return "thyme/sale/BattleSale";
	}
}
