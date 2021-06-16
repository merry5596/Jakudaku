<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>
<%@ include file="sidebar.jsp" %>
<div class="col-7 base">
	<div class="row">
		<div class="col-md-12">
			<c:if test="${!empty message}">
			  <b><font color="red"><c:url value="${message}" /></font></b>
			</c:if>
			
			<div align="center">
			  <form action='<c:url value="/user/signon.do"/>' method="POST">
			    <c:if test="${!empty signonForwardAction}">
			      <input type="hidden" name="forwardAction"
			        value='<c:url value="${signonForwardAction}"/>' />
			    </c:if>
			    <table>
			      <tr>
			        <td colspan="2">자꾸다꾸 로그인<br />&nbsp;
			        </td>
			      </tr>
			      <tr>
			        <td>Id:</td>
			        <td><input type="text" name="userId" size="20" /></td>
			      </tr>
			      <tr>
			        <td>Password:</td>
			        <td><input type="password" name="password" size="20" /></td>
			      </tr>
			      <tr>
			      	<td>&nbsp;</td>
	                <td><button type="submit" class="btn btn-warning">로그인</button></td>
			      </tr>
			    </table>
	            <p align="center">Not yet account <a href="/user/newAccount.do">Sign Up</a></p>
			  </form>
			  <a href='<c:url value="/shop/newAccount.do"/>'> 
			    <img border="0" src="../images/button_register_now.gif" alt="" />
			  </a>
			</div>
		</div>
	</div>
</div>

<%@ include file="bottom.jsp" %>