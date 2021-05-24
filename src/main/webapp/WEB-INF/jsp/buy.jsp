<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<HTML>
<HEAD>
<meta charset="UTF-8">
<TITLE>구매내역</TITLE>
</HEAD>
<BODY>
${Account.name} 님의 구매내역<br><br>
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
<br><br><br>

<h3>구매 목록</h3>
<br><br>

<table style="width:100%; border:1px solid #444444;">
	<c:forEach var="d" items="${orderMap}">
		<c:forEach var="o" items="${orderMap[d.key]}">
		<tr>
			<td>${d.key}</td>
			<td><button type="button"  onClick="location.href='/user/confirmOrder.do'">상세보기</button></td>
		</tr>
		<c:forEach var="l" items="${o.value.lineItems}">
			<tr>
			<td>${l.item.thumnail1}</td>
			<td onclick="location.href='/item/viewOnlineItem.do'"; style="cursor:pointer;" >${l.item.name}</td>
			<c:if test="${l.item.getClass().getSimpleName() eq Online}">
				<td>
				<c:if test="${not empty l.item.pcFile}">
					<a href="${l.item.pcFile}" download><button>pc용 다운로드</button></a>
				</c:if>
				<c:if test="${not empty l.item.tableFile}">
					<a href="${l.item.tableFile}" download><button>테블릿용 다운로드</button></a>
				</c:if>	
				<c:if test="${not empty l.item.phoneFile}">
					<a href="${l.item.phoneFile}" download><button>모바일용 다운로드</button></a>
				</c:if>	
				</td>
			</c:if>
			<td>
				<button type="button"  onClick="location.href='/review/writeReview.do'">리뷰쓰기</button>
			</td>
			</tr>
		</c:forEach>
		</c:forEach>
	</c:forEach>
</table>

<!--

<table>
	<c:forEach var="o" items="${orderList}">
		<tr>
			<td>${o.orderDate}</td>
			<td><button type="button"  onClick="location.href='/user/confirmOrder.do'">상세보기</button></td>
		</tr>
		<c:forEach var="l" items="${orderList.lineItems}">
			<tr>
			<td>${l.itemId.thumnail1}</td>
			<td onclick="location.href='/item/viewOnlineItem.do'"; style="cursor:pointer;" >${l.itemId.name}</td>
			<td>
			<c:if test="${not empty orderList.lineItems.itemId.pcFile}">
				<a href="${orderList.lineItems.itemId.pcFile}" download><button>pc용 다운로드</button></a>
			</c:if>
			<c:if test="${not empty orderList.lineItems.itemId.tableFile}">
				<a href="${orderList.lineItems.itemId.tableFile}" download><button>테블릿용 다운로드</button></a>
			</c:if>	
			<c:if test="${not empty orderList.lineItems.itemId.phoneFile}">
				<a href="${orderList.lineItems.itemId.phoneFile}" download><button>모바일용 다운로드</button></a>
			</c:if>	
			</td>
			</tr>
		</c:forEach>
	</c:forEach>
</table>


<a name = "funding" />
<h3>Funding 상품 구매 목록</h3>
<a href="#online">Online 상품 구매 목록 보기</a>
<br><br>

<table>
	<c:forEach var="f" items="${fundorderList}">
		<tr>
		<td>${f.orderDate}</td>
		<td><button type="button"  onClick="location.href='/user/confirmOrder.do'">상세보기</button></td>
		</tr>
		<tr>
		<td>${f.lineItem.itemId.thumnail1}</td>
		<td onclick="location.href='/item/viewFundingItem.do'"; style="cursor:pointer;" >${f.name}</td>
		</tr>
	</c:forEach>
</table>

  -->
</BODY>
</HTML>