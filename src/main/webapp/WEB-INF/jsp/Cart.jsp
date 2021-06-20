<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp"%>
<%@ include file="sidebar.jsp"%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css"
	integrity="sha512-8bHTC73gkZ7rZ7vpqUQThUDhqcNFyYi2xgDgPDHc+GXVGHXq+xPjynxIopALmOPqzo9JZj0k6OqqewdGO3EsrQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/components/table.min.css"
	integrity="sha512-NtvxKmBWfr6sEZ3y/qV4DTXPEXpP/VZZV5BEaCFxUukf7/cyktgYpfsxs2ERvisbDXfnLJfswd2DYEj0h+qAFA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/components/header.min.css"
	integrity="sha512-W7HgFS8NHLM8mfDtKDT7OBPvZORscUOJ6O9N0ejss8UdzlJttcZHCs9KZFjlUG6s+eIAnwApsSmrBSqo7aAwag=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/components/button.min.css" integrity="sha512-OD0ScwZAE5PCg4nATXnm8pdWi0Uk0Pp2XFsFz1xbZ7xcXvapzjvcxxHPeTZKfMjvlwwl4sGOvgJghWF2GRZZDw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/components/grid.min.css" integrity="sha512-RtktBL0Hhw9K2e2rGLZGadK8cF6Q0wKxRUkfh4pJszwnarmh3GbqHCiLm3UZFA1dRiFqtPkVrcby0hXWsqoDNA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<div class="col-7 base">
	<div class="row">
		<div class="col-md-12">


			<h1>Shopping Cart</h1>

			<br>

			<form action='<c:url value="/order/newOrder.do?"/>' method="post">
				<table class="ui very basic collapsing celled table center aligned middle aligned"
					style="width: 100%;" id="cart">
					<thead>
						<tr>
							<th>이미지</th>
							<th>상품</th>
							<th>판매가</th>
							<th>할인</th>
							<th>결제 금액</th>
							<th></th>
						</tr>
					</thead>
					<c:if test="${cart.numberOfItems == 0}">
						<tbody>
							<tr>
								<td style="text-align: center" colspan="6">
									<div class="content">
										<b>Your cart is empty.</b>
									</div>
								</td>
							</tr>
						</tbody>
					</c:if>

					<tbody>
						<c:forEach var="cartItem" items="${cart.cartItemList.pageList}">
							<tr>
								<td style="width: 120px; height: 120px"><a
									href='<c:url value="/item/viewOnlineItem.do">
			                                <c:param name="itemId" value="${cartItem.onlineItem.itemId}"/></c:url>'>
										<img src="${cartItem.onlineItem.thumbnail1Path}"
										style="max-width: 100%; max-height: 100%;" />
								</a><span></span></td>
								<td><b> <a
										href='<c:url value="/item/viewOnlineItem.do">
			                                 <c:param name="itemId" value="${cartItem.onlineItem.itemId}"/></c:url>'>
											<c:out value="${cartItem.onlineItem.name}" />
									</a>
										<div>
											옵션:
											<c:if test="${not empty cartItem.onlineItem.pcFile}">PC </c:if>
											<c:if test="${not empty cartItem.onlineItem.tabletFile}">TABLET </c:if>
											<c:if test="${not empty cartItem.onlineItem.phoneFile}">PHONE </c:if>
										</div></td>
								<td style="text-align: center"><c:out
										value="${cartItem.onlineItem.price}" /></td>
								<td style="text-align: center"><c:if
										test="${cartItem.onlineItem.saleState eq 1}"> 
															Time Sale
														<p>Time Sale</p>></c:if> <c:if
										test="${cartItem.onlineItem.saleState eq 2}"> 
															Battle Sale
															<p>Battle Sale</p>
									</c:if> -<c:out value="${cartItem.onlineItem.discount}" /></td>
								<td style="text-align: center"><c:out
										value="${cartItem.onlineItem.salePrice}" /></td>
								<td style="text-align: center">
									<button type="button" class="ui red basic button" 
										onclick="location.href='/order/newOrder.do?itemId=' + ${cartItem.onlineItem.itemId}">구매</button>
									
									<button type="button" class="ui red basic button" 
										onclick="location.href='/order/removeItemFromCart.do?workingItemId=' + ${cartItem.onlineItem.itemId}">삭제
									</button>
								</td>
							</tr>
						</c:forEach>
						
				
						
					<tr>
						<td colspan="6" style="font-size: 2.0rem;">
						<br>
						<br>
							결제 예정 금액 &nbsp; &nbsp; &nbsp;
							<b><span style="color: red;font-size: 2.5rem"><c:out value="${cart.actualTotal}" />원</span></b>
							
						<br>
						<br>
						<br>
						</td>
					</tr>
				</tbody>
			</table>
			
			<c:if test="${cart.numberOfItems > 0}">
				<div class="ui center aligned ten column grid">
					<div class="five wide column">
						<button type="submit" style="width:100%;" class="ui red huge button" >전체 구매</button>
					</div>
					
					<div class="five wide column">
						<button type="button" style="width:100%;" class="ui basic huge red button" 
								onclick="location.href='/order/removeItemFromCart.do'">전체 삭제
						</button>
					</div>
				</div>	
			</c:if>
			</form>
			
			<br>
			<br>
				<div style="text-align: center">
					<c:if test="${!cart.cartItemList.firstPage}">
						<a href='<c:url value="/order/viewCart.do?page=previousCart"/>'>
							<font color="gray"><B>&lt;&lt; Prev</B></font>
						</a>
					</c:if>
					<c:if test="${!cart.cartItemList.lastPage}">
						<a href='<c:url value="/order/viewCart.do?page=nextCart"/>'> <font
							color="gray"><B>Next &gt;&gt;</B></font>
						</a>
					</c:if>
				</div>

		</div>
	</div>
</div>


			
<%-- 				<c:if test="${cart.numberOfItems > 0}"> --%>
<!-- 					<br /> -->
<!-- 					<div style="text-align: center"> -->
<!-- 						<input type="submit" value="구매하기" /> <input type="image" src="" -->
<!-- 							name="buy" /> -->
<!-- 					</div> -->
<%-- 				</c:if> --%>
			



<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.js"
	integrity="sha512-dqw6X88iGgZlTsONxZK9ePmJEFrmHwpuMrsUChjAw1mRUhUITE5QU9pkcSox+ynfLhL15Sv2al5A0LVyDCmtUw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<%@ include file="bottom.jsp"%>

