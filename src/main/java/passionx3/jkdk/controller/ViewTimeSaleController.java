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

		if(day_of_week != 1 && day_of_week != 7){ //주말이 아니면 타임 세일 진행
			TimeSale timeSale = jkdkStore.getTimeSale();
			Online item = jkdkStore.getOnlineItemById(timeSale.getItemId());
			
			SimpleDateFormat sDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			cal.setTime(new Date());

			String now = sDate.format(cal.getTime());
			if(timeSale.getOpenTime().compareTo(now) > 0 ) {
				model.put("isSale", -1);
			}
			else if(timeSale.getOpenTime().compareTo(now) < 0 ) {
				if(timeSale.getCloseTime().compareTo(now) < 0 ) {
					model.put("isSale", 1);
				}
				else if(timeSale.getCloseTime().compareTo(now) > 0 ) {
					model.put("isSale", -1);
				}
			}
			
			model.put("timeSale", timeSale);
			model.put("item", item);
			
			
			return "thyme/sale/TimeSale";
		}
		else
			return "thyme/sale/TimeSale";
	}
}
