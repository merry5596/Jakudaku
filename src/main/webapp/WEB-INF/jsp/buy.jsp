<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="navbar.jsp" %>
<%@ include file="sidebar.jsp" %>
<div class="col-7 base">
<h2>${userSession.alias}  님의 구매내역</h2><br>
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
<br><br>

<table style="width:100%; border:1px solid #444444;">

	<c:if test="${fn:length(orderMap) == 0}">
			<span text-aline:center;>구매한 내역이 없습니다.</span>
	</c:if>
	
	<c:forEach var="d" items="${orderMap}">
		<c:forEach var="o" items="${orderMap[d.key]}">
			<tr>
				<td>${d.key}</td>
				<td><button type="button"  onClick="location.href='<c:url value="/order/viewOrder.do"><c:param name="orderId" value="${o.orderId}"/></c:url>'">상세보기</button></td>
			</tr>
			<c:forEach var="l" items="${o.lineItems}">
				
				<tr>
				<c:choose>
					<c:when test="${not empty l.online}">
						<td><img src="${l.online.thumbnail1}" style="width:100px; height:100px;"/></td>
						<td onclick="location.href='<c:url value="/item/viewOnlineItem.do"><c:param name="itemId" value="${l.itemId}"/></c:url>'"; style="cursor:pointer;" >${l.online.name}</td>
						<td>
							<c:if test="${not empty l.online.pcFile}">
								<a href="${l.online.pcFile}" download><button>pc용 다운로드</button></a>
							</c:if>
							<c:if test="${not empty l.online.tabletFile}">
								<a href="${l.online.tabletFile}" download><button>테블릿용 다운로드</button></a>
							</c:if>	
							<c:if test="${not empty l.online.phoneFile}">
								<a href="${l.online.phoneFile}" download><button>모바일용 다운로드</button></a>
							</c:if>	
						</td>
						<td>
							<button type="button"  onClick="location.href='<c:url value="/review/writeReview.do"><c:param name="lineItemId" value="${l.lineItemId}"/></c:url>'">리뷰쓰기</button>
						</td>
					</c:when>
					<c:otherwise>
						<td><img src="${l.funding.thumbnail1}" style="width:100px; height:100px;"/></td>
						<td onclick="location.href='<c:url value="/item/viewFundingItem.do"><c:param name="itemId" value="${l.itemId}"/></c:url>'"; style="cursor:pointer;" >${l.funding.name}</td>
						<td></td>
					</c:otherwise>
				</c:choose>
				
				</tr>
			</c:forEach>
			<tr><td><br></td></tr>
		</c:forEach>
	</c:forEach>
</table>
</div>
<%@ include file="bottom.jsp" %>

