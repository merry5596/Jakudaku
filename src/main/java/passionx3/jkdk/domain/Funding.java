package passionx3.jkdk.domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@SuppressWarnings("serial")
public class Funding extends Item implements Serializable {
		@NotBlank(message="펀딩종료 날짜/시간을 선택하세요.")
		private String finishDate;
	
		private int purchaseQuantity;
		
		@Positive(message="목표 수량을 입력하세요.")
		private int targetQuantity;

		public Funding() {
			super();
		}

		public String getFinishDate() {
			return finishDate;
		}

		public void setFinishDate(String finishDate) {
			this.finishDate = finishDate;
		}

		public int getPurchaseQuantity() {
			return purchaseQuantity;
		}

		public void setPurchaseQuantity(int purchaseQuantity) {
			this.purchaseQuantity = purchaseQuantity;
		}

		public int getTargetQuantity() {
			return targetQuantity;
		}

		public void setTargetQuantity(int targetQuantity) {
			this.targetQuantity = targetQuantity;
		}

		public int getRemainingDays() {
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date finish = null;
			try {
				finish = transFormat.parse(finishDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			Date today = new Date();
		
			int remainingDays = (int) ((finish.getTime() - today.getTime()) / (24*60*60*1000));
			
			return remainingDays;
		}

		
		public String getQuantityPercentage() {
			double percentage = purchaseQuantity / (double) targetQuantity * 100;
			String sPercentage = String.format("%d", (int)percentage);
			return sPercentage;
		}
		
		public boolean isFundingSuccess() {
			double per = purchaseQuantity / (double) targetQuantity;
			
			if(per >= 1) {
				return true;
			}
			else {
				return false;
			}
		}
		
		public boolean isFinishedFunding() {
			int remainingDay = getRemainingDays();
			
			if(remainingDay < 0) {
				return true;
			}
			else {
				return false;
			}
		}
		
}
