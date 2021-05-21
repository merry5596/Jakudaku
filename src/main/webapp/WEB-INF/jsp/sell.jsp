<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<HTML>
<HEAD>
<meta charset="UTF-8">
<TITLE>판매내역</TITLE>
<style>
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
<BODY>
${Account.name} 님의 판매내역<br><br>
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
<h3>online 상품 판매 목록</h3>
<a href="#online">Funding 상품 판매 목록 보기</a>
<br><br>
<table>
	<tr>
		<td>번호</td>
		<td>썸네일</td>
		<td>상품명</td>
		<td>등록날짜</td>
		<td>수정</td>
	</tr>
	<c:forEach var="o" items="${onlineList}" varState="status" >
		<tr>
		<td>${status.index}</td>
		<td>${o.thumnail1}</td>
		<td onclick="location.href='/item/viewOnlineItem.do'"; style="cursor:pointer;" >${o.name}</td>
		<td>${o.uploadDate}</td>
		<td><button type="button"  onClick="location.href='/item/sellOnlineItem.do'">수정</button></td>
		</tr>
	</c:forEach>
</table>
<br><br>
<a name = "funding" />
<h3>Funding 상품 판매 목록</h3>
<a href="#online">Online 상품 판매 목록 보기</a>
<br><br>
<table>
	<tr>
		<td>번호</td>
		<td>썸네일</td>
		<td>상품명</td>
		<td>등록날짜</td>
		<td>수정</td>
	</tr>
	<c:forEach var="f" items="${fundingList}" varState="status" >
		<tr>
		<td>${status.index}</td>
		<td>${f.thumnail1}</td>
		<td onclick="location.href='/item/viewOnlineItem.do'"; style="cursor:pointer;" >${f.name}</td>
		<td>${f.uploadDate}</td>
		<td><button type="button"  onClick="location.href='/item/sellOnlineItem.do'">수정</button></td>
		</tr>
	</c:forEach>
</table>
</BODY>
</HTML>