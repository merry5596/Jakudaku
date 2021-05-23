<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<HTML>
<HEAD>
<meta charset="UTF-8">
<TITLE>援щℓ�댁��</TITLE>
<style>
  table {
    width: 100%;
    border: 1px solid #444444;
  }
</style>
</HEAD>
<BODY>
${Account.name} ���� 援щℓ�댁��<br><br>
<!-- 異���援ы���ы��
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
    <th rowspan="2">議고��湲곌�</th>
    <th>�ㅻ��</th>
    <th>1二쇱��</th>
    <th>1媛���</th>
    <th>6媛���</th>
    <th>1��</th>
  </tr>
  <tr>
    <td colspan="5" align=center>��吏� ���ㅻ�� 怨�</td>
  </tr>
</thead>
</table> -->
<br><br><br>

<h3>援щℓ 紐⑸�</h3>
<br><br>

<table>
	<c:forEach var="d" items="${orderMap}">
		<c:forEach var="o" items="${orderMap[d.key]}">
		<tr>
			<td>${d.key}</td>
			<td><button type="button"  onClick="location.href='/user/confirmOrder.do'">���몃낫湲�</button></td>
		</tr>
		<c:forEach var="l" items="${o.value.lineItems}">
			<tr>
			<td>${l.item.thumnail1}</td>
			<td onclick="location.href='/item/viewOnlineItem.do'"; style="cursor:pointer;" >${l.item.name}</td>
			<c:if test="${l.item.getClass().getSimpleName() eq Online}">
				<td>
				<c:if test="${not empty l.item.pcFile}">
					<a href="${l.item.pcFile}" download><button>pc�� �ㅼ�대���</button></a>
				</c:if>
				<c:if test="${not empty l.item.tableFile}">
					<a href="${l.item.tableFile}" download><button>��釉�由우�� �ㅼ�대���</button></a>
				</c:if>	
				<c:if test="${not empty l.item.phoneFile}">
					<a href="${l.item.phoneFile}" download><button>紐⑤��쇱�� �ㅼ�대���</button></a>
				</c:if>	
				</td>
			</c:if>
			<td>
				<button type="button"  onClick="location.href='/review/writeReview.do'">由щ럭�곌린</button>
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
			<td><button type="button"  onClick="location.href='/user/confirmOrder.do'">���몃낫湲�</button></td>
		</tr>
		<c:forEach var="l" items="${orderList.lineItems}">
			<tr>
			<td>${l.itemId.thumnail1}</td>
			<td onclick="location.href='/item/viewOnlineItem.do'"; style="cursor:pointer;" >${l.itemId.name}</td>
			<td>
			<c:if test="${not empty orderList.lineItems.itemId.pcFile}">
				<a href="${orderList.lineItems.itemId.pcFile}" download><button>pc�� �ㅼ�대���</button></a>
			</c:if>
			<c:if test="${not empty orderList.lineItems.itemId.tableFile}">
				<a href="${orderList.lineItems.itemId.tableFile}" download><button>��釉�由우�� �ㅼ�대���</button></a>
			</c:if>	
			<c:if test="${not empty orderList.lineItems.itemId.phoneFile}">
				<a href="${orderList.lineItems.itemId.phoneFile}" download><button>紐⑤��쇱�� �ㅼ�대���</button></a>
			</c:if>	
			</td>
			</tr>
		</c:forEach>
	</c:forEach>
</table>


<a name = "funding" />
<h3>Funding ���� 援щℓ 紐⑸�</h3>
<a href="#online">Online ���� 援щℓ 紐⑸� 蹂닿린</a>
<br><br>

<table>
	<c:forEach var="f" items="${fundorderList}">
		<tr>
		<td>${f.orderDate}</td>
		<td><button type="button"  onClick="location.href='/user/confirmOrder.do'">���몃낫湲�</button></td>
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