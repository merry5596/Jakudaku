package passionx3.jkdk.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Funding extends Item implements Serializable {
		private String finishDate;
		private int purchaseQuantity;
		private int targetQuantity;

		public Funding() {
		}

		public Funding(String finishDate, int purchaseQuantity, int targetQuantity) {
			this.finishDate = finishDate;
			this.purchaseQuantity = purchaseQuantity;
			this.targetQuantity = targetQuantity;
		}
		
		public Funding(int itemId, String name, int price, int likeNum, String thumbnail1, int isForSale, String finishDate, int purchaseQuantity, int targetQuantity) {
			super(itemId, name, price, likeNum, thumbnail1, isForSale);
			this.finishDate = finishDate;
			this.purchaseQuantity = purchaseQuantity;
			this.targetQuantity = targetQuantity;
		}

		public Funding(int itemId, String producerId, String producerName, int themeId, String themeName,
				int categoryId, String name, Date uploadDate, int price, int likeNum, String thumbnail1,
				String thumbnail2, String thumbnail3, int isForSale, String description, int approval) {
			super();
			// TODO Auto-generated constructor stub
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

//		이 부분은 실시간으로 바뀌어야 돼서 javascript로 가져가서 구현하기

//		public int getRemainingDays() {
//			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			Date finish = null;
//			try {
//				finish = transFormat.parse(finishDate);
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			Date today = new Date();
//		
//			int remainingDays = (int) (finish.getTime() - today.getTime()) / (24*60*60*1000);
//			
//			return remainingDays;
//		}

		public int getRemainingDays() {
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

		
		public String getFinishDateExceptTime() {
			System.out.println(finishDate);
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date finish = null;
			try {
				finish = transFormat.parse(finishDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return transFormat.format(finish);
		}
		
		public String getQuantityPercentage() {
			double percentage = purchaseQuantity / (double) targetQuantity * 100;
			String sPercentage = String.format("%.2f", percentage);
			return sPercentage;
		}

}
