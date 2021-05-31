<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="navbar.jsp" %>
<%@ include file="sidebar.jsp" %>
<div class="col-7 base">
<!-- <style> 
  table {
    width: 100%;
    border-top: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border-bottom: 1px solid #444444;
    padding: 10px;
  }
</style>
</HEAD>
<BODY> -->
<h2>${userSession.alias} 님의 판매내역</h2><br><br>
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
<a href="#online">Funding 상품 판매 목록 보기</a>
<br><br>
<table style=" width: 100%;
    border-top: 1px solid #444444;
    border-collapse: collapse;">
	<tr style="border-bottom: 1px solid #444444;
    padding: 10px;">
		<td >번호</td>
		<td>썸네일</td>
		<td>상품명</td>
		<td>등록날짜</td>
		<td>수정</td>
	</tr>
	<c:if test="${fn:length(onlineList) == 0}">
			<td style="text-align:center" colspan="6"><b>판매한 내역이 없습니다.</b></td>
	</c:if>
	<c:forEach var="o" items="${onlineList}" varStatus="status" >
		<tr style="border-bottom: 1px solid #444444;
    padding: 10px;">
		<td>${status.index}</td>
		<td><img src="${o.thumbnail1}" style="width:100px; height:100px;"/></td>
		<td onclick="location.href='thyme/item/viewOnlineItem.do'"; style="cursor:pointer;" >${o.name}</td>
		<td>${o.uploadDate}</td>
		<td><button type="button" onClick="location.href='<c:url value="/item/sellOnlineItem.do"><c:param name="itemId" value="${o.itemId}"/></c:url>'">수정</button></td>
		</tr>
	</c:forEach>
</table>
<br><br>
<a name = "funding" />
<h3>Funding 상품 판매 목록</h3>
<a href="#online">Online 상품 판매 목록 보기</a>
<br><br>
<table style=" width: 100%;
    border-top: 1px solid #444444;
    border-collapse: collapse;">
	<tr style="border-bottom: 1px solid #444444;
    padding: 10px;">
		<td>번호</td>
		<td>썸네일</td>
		<td>상품명</td>
		<td>등록날짜</td>
		<td>수정</td>
	</tr>
	<c:if test="${fn:length(fundingList) == 0}">
			<td style="text-align:center" colspan="6"><b>판매한 내역이 없습니다.</b></td>
	</c:if>
	<c:forEach var="f" items="${fundingList}" varStatus="status" >
		<tr style="border-bottom: 1px solid #444444;
    padding: 10px;">
		<td>${status.index}</td>
		<td><img src="${f.thumbnail1}" style="width:100px; height:100px;""/></td>
		<td onclick="location.href='/item/viewOnlineItem.do'"; style="cursor:pointer;" >${f.name}</td>
		<td>${f.uploadDate}</td>
		<td><button type="button" onClick="location.href='<c:url value="/item/sellFundingItem.do"><c:param name="itemId" value="${f.itemId}"/></c:url>'">수정</button></td>

		</tr>
	</c:forEach>
</table>
</div>

<%@ include file="bottom.jsp" %>