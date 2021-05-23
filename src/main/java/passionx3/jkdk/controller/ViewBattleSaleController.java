package passionx3.jkdk.controller;

import org.springframework.beans.support.PagedListHolder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import passionx3.jkdk.domain.Online;
import passionx3.jkdk.domain.TimeSale;
import passionx3.jkdk.service.jkdkFacade;

@Controller
@SessionAttributes("userSession")
public class ViewBattleSaleController { 
	@Autowired
	private jkdkFacade jkdkStore;
	
	Calendar cal= Calendar.getInstance ( );		
	int day_of_week = cal.get ( Calendar.DAY_OF_WEEK );

	SimpleDateFormat sDate = new SimpleDateFormat("yy/MM/dd");
	
	@RequestMapping("/battlesale/viewBattleSale.do")
	public String handleRequest(ModelMap model) throws Exception {
		
		cal.setTime(new Date());
		String today = sDate.format(cal.getTime());
		
		if(day_of_week == 7){ //주말이면 타임 세일 진행
			BattleSale battleSale = jkdkStore.getBattleSale(today);
			Online item1 = jkdkStore.getOnlineItemById(battleSale.getItemId1());
			Online item2 = jkdkStore.getOnlineItemById(battleSale.getItemId2());
			
			model.put("battleSale", battleSale);
			model.put("item1", item1);
			model.put("item2", item2);
			
			return "thyme/sale/BattleSale";
		}
		else if (day_of_week == 1) {
			jkdkStore.resetVote();
			
			cal.add(Calendar.DATE, -1);
			String yesterday = sDate.format(cal.getTime());
			
			BattleSale battleSale = jkdkStore.getBattleSale(yesterday);
			Online item = jkdkStore.getOnlineItemById(jkdkStore.getWinnerItemId());
			item.setCategoryName(jkdkStore.getCategoryNameByCategoryId(item.getCategoryId()));
			model.put("battleSale", battleSale);
			model.put("item", item);
			
			return "thyme/sale/BattleWinner";
		}
			
		return "thyme/sale/BattleSale";
	}
	
	@RequestMapping("/battlesale/vote1.do")
	public String voteBattleItem1(ModelMap model, @RequestParam("battleSaleId") String battleSaleId, 
			@RequestParam(value = "userId", required=false) String userId
			) throws Exception {
		cal.setTime(new Date());
		String today = sDate.format(cal.getTime());
		
		if(jkdkStore.insertVote(battleSaleId, userId) == 1) {
			jkdkStore.updateBattleSaleVote1(battleSaleId);
			return "redirect:/battlesale/viewBattleSale.do";
		}
		
		BattleSale battleSale = jkdkStore.getBattleSale(today);
		Online item1 = jkdkStore.getOnlineItemById(battleSale.getItemId1());
		Online item2 = jkdkStore.getOnlineItemById(battleSale.getItemId2());
		
		model.put("battleSale", battleSale);
		model.put("item1", item1);
		model.put("item2", item2);
		
		String alreadyVote = "-1";
		model.put("alreadyVote", alreadyVote);
	
		return "thyme/sale/BattleSale";
		
	}
	
	@RequestMapping("/battlesale/vote2.do")
	public String voteBattleItem2(ModelMap model, @RequestParam("battleSaleId") String battleSaleId,
			@RequestParam(value = "userId", required=false) String userId
			) throws Exception {
		cal.setTime(new Date());
		String today = sDate.format(cal.getTime());
		
		if(jkdkStore.insertVote(battleSaleId, userId) == 1) {
			jkdkStore.updateBattleSaleVote2(battleSaleId);
			return "redirect:/battlesale/viewBattleSale.do";
		}
		
		
		BattleSale battleSale = jkdkStore.getBattleSale(today);
		Online item1 = jkdkStore.getOnlineItemById(battleSale.getItemId1());
		Online item2 = jkdkStore.getOnlineItemById(battleSale.getItemId2());
		
		model.put("battleSale", battleSale);
		model.put("item1", item1);
		model.put("item2", item2);
		
		String alreadyVote = "-1";
		model.put("alreadyVote", alreadyVote);
	
		return "thyme/sale/BattleSale";
		
	}
}
