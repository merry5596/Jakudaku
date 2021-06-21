<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="navbar.jsp" %>
<%@ include file="sidebar.jsp" %>


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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/components/icon.min.css" integrity="sha512-8Tb+T7SKUFQWOPIQCaLDWWe1K/SY8hvHl7brOH8Nz5z1VT8fnf8B+9neoUzmFY3OzkWMMs3OjrwZALgB1oXFBg==" crossorigin="anonymous" referrerpolicy="no-referrer" />

<style>
	.ui.table tr td { border-top: 0px !important; }
</style>

<div class="col-7 base">
<h1>${userSession.alias} 님의 구매내역</h1><br><br>
<!-- 추후구현사항
<table style="undefined;table-layout: fixed; width: 164px">
<colgroup>
<col style="width: 100px">
<col style="width: 100px">
<col style="width: 100px">
<col style="width: 100px">
<col style="width: 100px">
<col style="width: 100px">
</colgroup>
<thead>
  <tr>
    <th rowspan="2">조회기간</th>
    <th>오늘</th>
    <th>1주일</th>
    <th>1개월</th>
    <th>6개월</th>
    <th>1년</th>
  </tr>
  <tr>
    <td colspan="5" align=center>날짜 나오는 곳</td>
  </tr>
</thead>
</table> -->

<h3>구매 목록</h3>

<table style="width: 100%;border-collapse: collapse;" class="ui very basic table left aligned middle aligned">

	<c:if test="${fn:length(orderMap) == 0}">
			<span style="text-align:center;">구매한 내역이 없습니다.</span>
	</c:if>
	
	<c:forEach var="d" items="${orderMap}">
		<c:forEach var="o" items="${orderMap[d.key]}">
			<tr>
				<td>${d.key}</td>
				<c:choose>
				<c:when test = "${o.isOrder}">
				<td><button type="button" class="ui mini left aligned button" onClick="location.href='<c:url value="/order/viewOrder.do"><c:param name="orderId" value="${o.orderId}"/></c:url>'">상세보기</button></td>
				</c:when>
				<c:otherwise>
				<td><button type="button" class="ui mini left aligned button" onClick="location.href='<c:url value="/order/viewFundOrder.do"><c:param name="orderId" value="${o.orderId}"/></c:url>'">상세보기</button></td>
				</c:otherwise>
				</c:choose>
			</tr>
			<c:forEach var="l" items="${o.lineItems}">
				
				<tr>
				<c:choose>
					<c:when test="${not empty l.online}">					
						<td onclick="location.href='<c:url value="/item/viewOnlineItem.do"><c:param name="itemId" value="${l.itemId}"/></c:url>'"; style="cursor:pointer;" ><img src="${l.online.thumbnail1Path}" style="width:100px; height:100px;"/></td>
						<td onclick="location.href='<c:url value="/item/viewOnlineItem.do"><c:param name="itemId" value="${l.itemId}"/></c:url>'"; style="cursor:pointer;" >${l.online.name}</td>
						<td>
							<c:if test="${not empty l.online.pcFile}">
								<button class="ui red inverted circular button" onclick="location.href='<c:url value="/item/download.do">
								<c:param name="itemId" value="${l.itemId}"/>
								<c:param name="uploadDate" value="${l.uploadDate}"/>
								<c:param name="fileName" value="${l.online.pcFile}"/></c:url>'">pc용 &nbsp;&nbsp;<i class="download icon"></i></button>
							</c:if>
							<c:if test="${not empty l.online.tabletFile}">
								<button class="ui red inverted circular button" onclick="location.href='<c:url value="/item/download.do">
								<c:param name="itemId" value="${l.itemId}"/>
								<c:param name="uploadDate" value="${l.uploadDate}"/>
								<c:param name="fileName" value="${l.online.tabletFile}"/></c:url>'">태블릿용 &nbsp;&nbsp;<i class="download icon"></i></button>
							</c:if>	
							<c:if test="${not empty l.online.phoneFile}">
								<button class="ui red inverted circular button" onclick="location.href='<c:url value="/item/download.do">
								<c:param name="itemId" value="${l.itemId}"/>
								<c:param name="uploadDate" value="${l.uploadDate}"/>
								<c:param name="fileName" value="${l.online.phoneFile}"/></c:url>'">모바일용 &nbsp;&nbsp;<i class="download icon"></i></button>
							</c:if>	
						</td>
						<td>
							<button type="button" class="ui red button" onClick="location.href='<c:url value="/review/writeReview.do"><c:param name="lineItemId" value="${l.lineItemId}"/></c:url>'">리뷰쓰기</button>
						</td>
					</c:when>
					<c:otherwise>
						<td onclick="location.href='<c:url value="/item/viewFundingItem.do"><c:param name="itemId" value="${l.itemId}"/></c:url>'"; style="cursor:pointer;" ><img src="${l.funding.thumbnail1Path}" style="width:100px; height:100px;"/></td>
						<td onclick="location.href='<c:url value="/item/viewFundingItem.do"><c:param name="itemId" value="${l.itemId}"/></c:url>'"; style="cursor:pointer;" >${l.funding.name}</td>
						<td>
						<c:if test = "${!l.funding.isFundingSuccess()}">
							<c:choose>
							<c:when test="${l.funding.isFinishedFunding()}">펀딩실패</c:when>
							<c:otherwise>펀딩중</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test = "${l.funding.isFundingSuccess()}">펀딩성공</c:if>
						</td>
						
					</c:otherwise>
				</c:choose>
				
				</tr>
			</c:forEach>
		</c:forEach>
	</c:forEach>
</table>
</div>



<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.js"
	integrity="sha512-dqw6X88iGgZlTsONxZK9ePmJEFrmHwpuMrsUChjAw1mRUhUITE5QU9pkcSox+ynfLhL15Sv2al5A0LVyDCmtUw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	
<%@ include file="bottom.jsp" %>