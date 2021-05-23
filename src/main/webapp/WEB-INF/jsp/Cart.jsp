<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
    <!-- side bar-->
    <div class="row row-margin">
        <div class="col-3 sidebar d-flex flex-column p-3 bg-light" style="width: 250px;">
            <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
                <svg class="bi me-2" width="40" height="32">
                    <use xlink:href="#bootstrap"></use>
                </svg>
                <span class="fs-4">Sidebar</span>
            </a>
            <hr>
            <ul class="nav nav-pills flex-column mb-auto">
                <li>
                    <a href="#" class="nav-link link-dark">
                        <svg class="bi me-2" width="16" height="16">
                            <use xlink:href="#speedometer2"></use>
                        </svg>
                        Diary
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link link-dark">
                        <svg class="bi me-2" width="16" height="16">
                            <use xlink:href="#table"></use>
                        </svg>
                        Font
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link link-dark">
                        <svg class="bi me-2" width="16" height="16">
                            <use xlink:href="#grid"></use>
                        </svg>
                        Wallpaper
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link link-dark">
                        <svg class="bi me-2" width="16" height="16">
                            <use xlink:href="#people-circle"></use>
                        </svg>
                        Icon
                    </a>
                </li>
            </ul>
        </div>
        <div class="col-7 base">
			<table style="width:1000px; align:center;">
			  <tr style="vertical-align:top">
			    <td style="text-align:center">
			      <h2>Shopping Cart</h2>
			      <form action='<c:url value="/order/newOrder.do?"/>' method="post">
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
			                  <c:out value="${cartItem.item.name}" /></a>
			                <br><br>옵션:
			                <c:if test="${cartitem.item.pcFile != ''}">PC </c:if>
			                <c:if test="${cartItem.item.tabletFile != ''}">TABLET </c:if>
			                <c:if test="${cartItem.item.phoneFile != ''}">PHONE </c:if>
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
							<br>-<c:out value="${cartItem.item.discount}"/></td>
			              <td style="text-align:center"><c:out value="${cartItem.item.salePrice}" /></td>
			              <td style="text-align:center">
			                <button type="button" onclick="location.href='/order/newOrder.do?itemId=' + ${cartItem.item.itemId}">구매</button>
			                <br><button type="button" onclick="location.href='/user/removeItemFromCart.do?workingItemId=' + ${cartItem.item.itemId}">삭제</button></td>
			            </tr>
			          </c:forEach>
			          <tr bgcolor="#cccccc">
			          	<td colspan="3" align="left">
			          		<button type="button" onclick="location.href='/user/removeItemFromCart.do?">전체 삭제</button>
			          	</td>
			            <td colspan="3" align="right">
			            	<b>총 <c:out value="${cart.subTotal}" />원</b><br><br>
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
		</div>
	</div>
</body>

</html>