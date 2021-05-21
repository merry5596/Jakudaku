<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>

<table style="width:1000px; align:center;">
  <tr style="vertical-align:top">
    <td style="text-align:center">
      <h2>Shopping Cart</h2>
      <form action='<c:url value="/shop/newOrder.do"/>' method="post">
        <table style="width:100%;" id="cart">
          <tr bgcolor="#cccccc">
            <td style="text-align:center"><b>이미지</b></td>
			<td style="text-align:center"><b>상품</b></td>
 			<td style="text-align:center"><b>판매가</b></td>
 			<td style="text-align:center"><b>할인</b></td>
 			<td style="text-align:center"><b>결제 금액</b></td>
 			<td style="text-align:center">&nbsp;</td>
          </tr>

          <c:if test="${cart.numberOfItems == 0}">
            <tr bgcolor="#EEEEEE">
              <td style="text-align:center" colspan="6"><b>Your cart is empty.</b></td>
            </tr>
          </c:if>

          <c:forEach var="cartItem" items="${cart.cartItemList.pageList}">
            <tr bgcolor="#EEEEEE">
              <td><a href='<c:url value="/item/viewOnlineItem.do">
                  <c:param name="itemId" value="${cartItem.item.itemId}"/></c:url>'>
                  <img src="${cartItem.item.thumbnail1}}"/></a></td>
              <td><b>
                <a href='<c:url value="/item/viewOnlineItem.do">
                  <c:param name="itemId" value="${cartItem.item.itemId}"/></c:url>'>
                  <c:out value="${cartItem.item.name}" /> </a>
                 <br><br>ìµì:
                <c:choose>
					<c:when test="${cartItem.deviceOption == 0}">PC</c:when>
					<c:when test="${cartItem.deviceOption == 1}">TABLET</c:when>
					<c:when test="${cartItem.deviceOption == 2}">PHONE</c:when>
				</c:choose>
		    	<!-- <select id="deviceSelect" onchange="changeOption()">
		    		<option value="0">PC</option>
		    		<option value="1">TABLET</option>
		    		<option value="2">PHONE</option>
		    	</select>
 				<script>
	 			 	function changeOption() {
	 			 		alert('hello');
	 			 		var deviceSelect = document.getElementById('deviceSelect');
	 			 		var deviceValue = deviceSelect.options[deviceSelect.selectedIndex].value;
	 			 		var test = document.getElementById('test');
	 			 		test.innerHTML"Changed option: " + deviceValue;
	 			 	}
	 		   </script> -->
               </b></td>
              <td style="text-align:center"><c:out value="${cartItem.item.price}" /></td>
              <td style="text-align:center">
              	<c:choose>
	              	<c:when test="cartItem.item.saleState == 0"> 	<!-- No Sale -->
	              		<c:set var="discountPrice" value="0" /></c:when>
	              	<c:when test="cartItem.item.saleState == 1">	<!-- Time Sale -->
	              		<c:set var="discountPrice" value="${cartItem.item.price} * 0.1" />
						Time Sale</c:when>
	              	<c:when test="cartItem.item.saleState == 2">	<!-- Battle Sale -->
	              		<c:set var="discountPrice" value="${cartItem.item.price} * 0.1" />
						Battle Sale</c:when></c:choose>
				<br>-<c:out value="${discountPrice}"/></td>
              <td style="text-align:center"><c:out value="${cartItem.item.price} - ${discountPrice}" /></td>
              <td style="text-align:center">
                <button type="button">구매</button>
                <br><button type="button">삭제</button></td>
            </tr>
          </c:forEach>
          <tr bgcolor="#cccccc">
          	<td colspan="3" align="left">
          		<button type="button">전체 삭제</button>
          	</td>
            <td colspan="3" align="right">
            	<b>총 <c:out value="${cart.subTotal}" />원</b><br><br>
            </td>
          </tr>
        </table>
        
        <div style="text-align:center">
          <c:if test="${!cart.cartItemList.firstPage}">
            <a href='<c:url value="viewCart.do?page=previousCart"/>'>
              <font color="green"><B>&lt;&lt; Prev</B></font></a>
          </c:if>
          <c:if test="${!cart.cartItemList.lastPage}">
            <a href='<c:url value="viewCart.do?page=nextCart"/>'>
              <font color="green"><B>Next &gt;&gt;</B></font></a>
          </c:if>
        </div>
        <c:if test="${cart.numberOfItems > 0}">
	      <br />
	      <div style="text-align:center">
  			<input type="submit" value="구매하기" />
        	<!-- <input type="image" src="" name="buy" /> -->
	      </div>
        </c:if>
      </form> 
    </td>
  </tr>
</table>