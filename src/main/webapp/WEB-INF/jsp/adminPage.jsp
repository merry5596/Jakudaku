<jsp:include page="header.jsp" />
<!DOCTYPE html>
<html>

<body>
  <!--navbar-->
  <nav class="mainnav navbar navbar-expand-lg navbar-light sticky-top bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="/">Admin</a>
      <c:out value="${user.name}" />
    </div>
  </nav>
  
	<div>
		<p>승인 요청 목록</p>
		<div class="wrap">
		   <div class="search">
		      <input type="text" class="searchTerm" placeholder="What are you looking for?">
		      <button type="submit" class="searchButton">
		        <i class="fa fa-search"></i>
		     </button>
		   </div>
		</div>
		
		<!--  승인 대기 상품 목록 -->
		<div align="center">
		  <table class="n23">
		    <tr bgcolor="#CCCCCC">
		      <td>&nbsp;</td>
		      <td><b>Product ID</b></td>
		      <td><b>Name</b></td>
		    </tr>
		    <c:forEach var="product" items="${productList.pageList}">
		      <tr bgcolor="#FFFF88">
		        <td><a
		          href='<c:url value="/shop/viewProduct.do"><c:param name="productId" value="${product.productId}"/></c:url>'>
		            <c:out value="${product.description}" escapeXml="false" />
		        </a></td>
		        <td><b><a
		            href='<c:url value="/shop/viewProduct.do"><c:param name="productId" value="${product.productId}"/></c:url>'>
		              <font color="BLACK"><c:out value="${product.productId}" /></font>
		          </a></b></td>
		        <td><c:out value="${product.name}" /></td>
		      </tr>
		    </c:forEach>
		    <tr>
		      <td><c:if test="${!productList.firstPage}">
		          <a href="?page=previous"><font color="white"><B>&lt;&lt;
		                Prev</B></font></a>
		        </c:if> <c:if test="${!productList.lastPage}">
		          <a href="?page=next"><font color="white"><B>Next
		                &gt;&gt;</B></font></a>
		        </c:if></td>
		    </tr>
		  </table>
		</div>
	</div>
</body>
</html>