package passionx3.jkdk.controller;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import passionx3.jkdk.domain.Online;
import passionx3.jkdk.domain.TimeSale;
import passionx3.jkdk.service.jkdkFacade;

@Controller
@SessionAttributes("userSession")
public class ViewTimeSaleController { 
	
	@Autowired
	private jkdkFacade jkdkStore;
	
	@RequestMapping("/timesale/viewtimeSale.do")
	public String handleRequest(ModelMap model) throws Exception {
		
		Calendar cal= Calendar.getInstance ( );	
		int day_of_week = cal.get ( Calendar.DAY_OF_WEEK );

		SimpleDateFormat sDate = new SimpleDateFormat("yy/MM/dd");
		cal.setTime(new Date());
		String today = sDate.format(cal.getTime());

		if(day_of_week != 1 && day_of_week != 7){ //주말이 아니면 타임 세일 진행
			TimeSale timeSale = jkdkStore.getTimeSale(today);
			
			Online item = jkdkStore.getOnlineItemById(timeSale.getItemId());
			item.setCategoryName(jkdkStore.getCategoryNameByCategoryId(item.getCategoryId()));
			int salePrice = item.getPrice() * timeSale.getDiscountRate();
			
			model.put("salePrice", salePrice);
			model.put("timeSale", timeSale);
			model.put("timeSale", timeSale);
			model.put("item", item);
			return "thyme/sale/TimeSale";
		}
		else
			return "thyme/sale/TimeSale";
	}
}
