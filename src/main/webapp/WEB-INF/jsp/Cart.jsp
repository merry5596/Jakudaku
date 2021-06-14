<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<%@ include file="sidebar.jsp" %>
        <div class="col-7 base">
           <div class="row">
            <div class="col-md-12">
               <table style="width:100%; align:center;">
                 <tr style="vertical-align:top">
                   <td style="text-align:center">
                     <h2>Shopping Cart</h2>
                     <form action='<c:url value="/order/newOrder.do?"/>' method="post">
                       <table style="width:100%;" id="cart">
                         <tr bgcolor="#cccccc">
                           	<td style="text-align:center"><b>이미지 </b></td>
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
                             <td style="width: 120px; height:120px"><a href='<c:url value="/item/viewOnlineItem.do">
                                 <c:param name="itemId" value="${cartItem.onlineItem.itemId}"/></c:url>'>
                                 <img src="${cartItem.onlineItem.thumbnail1}" style="max-width: 100%; max-height: 100%;"/></a><span></span></td>
                             <td><b>
                               <a href='<c:url value="/item/viewOnlineItem.do">
                                 <c:param name="itemId" value="${cartItem.onlineItem.itemId}"/></c:url>'>
                                 <c:out value="${cartItem.onlineItem.name}" /></a>
                               <div>옵션:
                               	 <c:if test="${not empty cartItem.onlineItem.pcFile}">PC </c:if>
                               	 <c:if test="${not empty cartItem.onlineItem.tabletFile}">TABLET </c:if>
                               	 <c:if test="${not empty cartItem.onlineItem.phoneFile}">PHONE </c:if> </div></td>
                             <td style="text-align:center"><c:out value="${cartItem.onlineItem.price}" /></td>
                             <td style="text-align:center">
                                  <c:if test="${cartItem.onlineItem.saleState eq 1}">   <!-- Time Sale -->
                               	  <p>Time Sale</p>></c:if>
                                  <c:if test="${cartItem.onlineItem.saleState eq 2}">   <!-- Battle Sale -->
                                  <p>Battle Sale</p></c:if>
                           		 -<c:out value="${cartItem.onlineItem.discount}"/></td>
                             <td style="text-align:center"><c:out value="${cartItem.onlineItem.salePrice}" /></td>
                             <td style="text-align:center">
                               <button type="button" onclick="location.href='/order/newOrder.do?itemId=' + ${cartItem.onlineItem.itemId}">구매</button>
                               <br><button type="button" onclick="location.href='/order/removeItemFromCart.do?workingItemId=' + ${cartItem.onlineItem.itemId}">삭제 </button></td>
                           </tr>
                         </c:forEach>
                         <tr bgcolor="#cccccc">
                            <td colspan="3" align="left">
                               <button type="button" onclick="location.href='/order/removeItemFromCart.do'">전체 삭제</button>
                            </td>
                           <td colspan="3" align="right">
                              <b>총 <c:out value="${cart.actualTotal}" />원</b><br><br>
                           </td>
                         </tr>
                       </table>
                       
                       <div style="text-align:center">
                         <c:if test="${!cart.cartItemList.firstPage}">
                           <a href='<c:url value="/order/viewCart.do?page=previousCart"/>'>
                             <font color="green"><B>&lt;&lt; Prev</B></font></a>
                         </c:if>
                         <c:if test="${!cart.cartItemList.lastPage}">
                           <a href='<c:url value="/order/viewCart.do?page=nextCart"/>'>
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
      </div>
<%@ include file="bottom.jsp" %>