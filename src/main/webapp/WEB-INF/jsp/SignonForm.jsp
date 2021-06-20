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

<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/components/form.min.css" integrity="sha512-GqdyivmcmveCy9pRf2yTD/TPYXVmbPFDojvy15i3xuLLy9nkgNhDRMk6+Dt/uYDdnItFUFF7WqW7GoGv+gPX+A==" crossorigin="anonymous" referrerpolicy="no-referrer" /> -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/components/icon.min.css" integrity="sha512-8Tb+T7SKUFQWOPIQCaLDWWe1K/SY8hvHl7brOH8Nz5z1VT8fnf8B+9neoUzmFY3OzkWMMs3OjrwZALgB1oXFBg==" crossorigin="anonymous" referrerpolicy="no-referrer" /> -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/components/button.min.css" integrity="sha512-OD0ScwZAE5PCg4nATXnm8pdWi0Uk0Pp2XFsFz1xbZ7xcXvapzjvcxxHPeTZKfMjvlwwl4sGOvgJghWF2GRZZDw==" crossorigin="anonymous" referrerpolicy="no-referrer" /> -->
<!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css" integrity="sha512-8bHTC73gkZ7rZ7vpqUQThUDhqcNFyYi2xgDgPDHc+GXVGHXq+xPjynxIopALmOPqzo9JZj0k6OqqewdGO3EsrQ==" crossorigin="anonymous" referrerpolicy="no-referrer" /> -->

<!-- <div class="ui middle aligned center aligned grid"> -->
<!--   <div class="column"> -->
<!--     <h2 class="ui teal image header"> -->
<!-- <!--       <img th:src="@{/image/logo_square.png}" class="image"> --> 
<!--       <div class="content"> -->
<!--         자꾸다꾸 Log-in -->
<!--       </div> -->
<!--     </h2> -->
<!--     <form class="ui large form"> -->
<!--       <div class="ui stacked segment"> -->
<!--         <div class="field"> -->
<!--           <div class="ui left icon input"> -->
<!--             <i class="user icon"></i> -->
<!--             <input type="text" name="email" placeholder="ID"> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="field"> -->
<!--           <div class="ui left icon input"> -->
<!--             <i class="lock icon"></i> -->
<!--             <input type="password" name="password" placeholder="Password"> -->
<!--           </div> -->
<!--         </div> -->
<!--         <div class="ui fluid large red submit button">로그인</div> -->
<!--       </div> -->

<!--       <div class="ui error message"></div> -->

<!--     </form> -->

<!--     <div class="ui message"> -->
<!--       자꾸다꾸가 처음이신가요? <a href="#">회원가입</a> -->
<!--     </div> -->
<!--   </div> -->
<!-- </div> -->

<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/components/form.min.js" integrity="sha512-VM6WNFlVLFwKXphssthAXJpG3cKWUK3G4edfsydLITA4iSeZmvJ+2gKBrR6qYkt/3A/I8hDHnAuIBz7xtfVtOg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.js" integrity="sha512-dqw6X88iGgZlTsONxZK9ePmJEFrmHwpuMrsUChjAw1mRUhUITE5QU9pkcSox+ynfLhL15Sv2al5A0LVyDCmtUw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script> -->
<script>

</script>

<%@ include file="bottom.jsp" %>

