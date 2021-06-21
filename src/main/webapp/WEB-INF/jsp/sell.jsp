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
<div class="col-7 base">


<h1>${userSession.alias} 님의 판매내역</h1><br><br>

			<br>

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

<a name = "online" />
<h3>Online 상품 판매 목록</h3>
<a href="#funding">Funding 상품 판매 목록 보기</a>
<br><br>
<table style="width: 100%;" class="ui very basic collapsing celled table center aligned middle aligned">
	<thead>
						<tr>
							<th>번호</th>
							<th>썸네일</th>
							<th>상품명</th>
							<th>등록날짜</th>
							<th>승인여부</th>
							<th>수정</th>
							<th>펀딩가능여부</th>
						</tr>
					</thead>
 <tbody>
   <c:if test="${fn:length(onlineList) == 0}">
         <td style="text-align:center" colspan="6"><b>판매한 내역이 없습니다.</b></td>
   </c:if>
   <c:forEach var="o" items="${onlineList}" varStatus="status" >
      <tr>
      <td>${status.index + 1}</td>
      <td onclick="location.href='<c:url value="/item/viewOnlineItem.do"><c:param name="itemId" value="${o.itemId}"/></c:url>'"; style="cursor:pointer;" ><img src="${o.thumbnail1Path}" style="width:100px; height:100px;"/></td>
      <td onclick="location.href='<c:url value="/item/viewOnlineItem.do"><c:param name="itemId" value="${o.itemId}"/></c:url>'"; style="cursor:pointer;" >${o.name}</td>
      <td>${o.uploadDate}</td>
      <td>
         <c:choose>
         <c:when test="${o.approval eq 1}">승인완료</c:when>
         <c:when test="${o.approval eq -1}">승인거부</c:when>
         <c:otherwise>승인대기</c:otherwise>
         </c:choose>
      </td>
      <td><button type="button" class="ui button" onClick="location.href='<c:url value="/item/sellOnlineItem.do"><c:param name="itemId" value="${o.itemId}"/></c:url>'">수정</button></td>
      <td>
         <c:if test="${o.like >= 50}">
            <button type="button" class="ui button" onClick="location.href='/item/sellFundingItem.do'">펀딩 시작</button>
         </c:if>
      </td>
      </tr>
   </c:forEach>
   </tbody>
</table>
<br><br>
<a name = "funding" />
<h3>Funding 상품 판매 목록</h3>
<a href="#online">Online 상품 판매 목록 보기</a>

<br><br>

<p style="font-size: 0.8rem; color:gray;">*펀딩 상품은 수정이 불가능합니다.</p>

<br><br>
<table style="width: 100%;" class="ui very basic collapsing celled table center aligned middle aligned">
   <thead>
   <tr>
      <th>번호</th>
      <th>썸네일</th>
      <th>상품명</th>
      <th>등록날짜</th>
      <th>승인여부</th>
   </tr>
   </thead>
   <c:if test="${fn:length(fundingList) == 0}">
         <td style="text-align:center" colspan="6"><b>판매한 내역이 없습니다.</b></td>
   </c:if>
   <c:forEach var="f" items="${fundingList}" varStatus="status" >
      <tr >
      <td>${status.index}</td>
      <td onclick="location.href='<c:url value="/item/viewFundingItem.do"><c:param name="itemId" value="${f.itemId}"/></c:url>'"; style="cursor:pointer;" ><img src="${f.thumbnail1Path}" style="width:100px; height:100px;""/></td>
      <td onclick="location.href='<c:url value="/item/viewFundingItem.do"><c:param name="itemId" value="${f.itemId}"/></c:url>'"; style="cursor:pointer;" >${f.name}</td>
      <td>${f.uploadDate}</td>
      <td>
         <c:choose>
         <c:when test="${f.approval eq 1}">승인완료</c:when>
         <c:when test="${f.approval eq -1}">승인거부</c:when>
         <c:otherwise>승인대기</c:otherwise>
         </c:choose>
      </td>

      </tr>
   </c:forEach>
</table>
</div>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.js"
	integrity="sha512-dqw6X88iGgZlTsONxZK9ePmJEFrmHwpuMrsUChjAw1mRUhUITE5QU9pkcSox+ynfLhL15Sv2al5A0LVyDCmtUw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<%@ include file="bottom.jsp" %>