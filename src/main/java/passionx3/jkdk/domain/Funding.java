package passionx3.jkdk.domain;

import java.util.Date;

public class Funding extends Item {
		private Date finishDate;
		private int purchaseQuantity;
		private int targetQuantity;
		private boolean isSaleEnded;

		public Date getFinishDate() {
			return finishDate;
		}

		public void setFinishDate(Date finishDate) {
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

		public boolean isSaleEnded() {
			return isSaleEnded;
		}

		public void setSaleEnded(boolean isSaleEnded) {
			this.isSaleEnded = isSaleEnded;
		}

//		이 부분은 실시간으로 바뀌어야 돼서 javascript로 가져가서 구현하기
//		public String getRemainingTime() {
//			Date today = new Date();
//		
//			long remainingDays = (finishDate.getTime() - today.getTime()) / (24*60*60*1000);
//			long remainingHours = (finishDate.getTime() - today.getTime()) % (24*60*60*1000) / (60*60*1000);
//			long remainingMinutes = (finishDate.getTime() - today.getTime()) % (60*60*1000) / (60 * 1000);
//			long remainingSeconds = (finishDate.getTime() - today.getTime()) % (60 * 1000) / (1000);
//			
//			return remainingDays + "일 " + remainingHours + "시간" + remainingMinutes + "분" + remainingSeconds + "초";
//		}
		
//		소수점 자르는 과정에서 double -> String, String -> double 을 거치는데
//		출력용이면 그냥 반환값을 String으로 바꿔서 'return sPercentage;' 해도 되지 않을까
		public double getQuantityPercentage() {
			double percentage = (double) purchaseQuantity / targetQuantity * 100;
			String sPercentage = String.format("%.2f", percentage);
			return Double.parseDouble(sPercentage);
		}

}
