<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<body>
<div class="wrap">
  <!--navbar-->
  <div align="center">
  <nav class="mainnav navbar navbar-expand-lg navbar-light sticky-top bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="/"><b>자꾸다꾸</b></a>

      <!--검색 창-->
      <div class="row">
        <div class="col-5">
          <form class="d-flex" action="/item/searchItems.do" method="get">
            <input class="form-control me-2" size="50" name="keyword" id="keyword" type="text" placeholder="전체 상품 검색" aria-label="Search" />
            <button class="btn btn-outline-success" type="submit">Search</button>
          </form>
        </div>

        <div class="col-4">
	        <div class="row">
	        	<c:if test="${empty userSession}">
			        <div class="col-5">
			          <!-- Button trigger modal -->
			          <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modalForm">
			            login
			          </button>        
			         </div>  
		         </c:if>
		         
		          <!-- 장바구니 -->
		         <div class= "col-3">
			         <form action="/order/viewCart.do">
				         <button type="submit" class="btn btn-secondary">
			                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bag" viewBox="0 0 16 16">
							  <path d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1zm3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4h-3.5zM2 5h12v9a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V5z"></path>
							</svg>
			              </button>
		              </form>
		         </div>
		       
		      	 <c:if test="${not empty userSession}">
			         <div class="col">
			         	 <c:if test="${userSession.userId eq 'admin'}">
					         <div class="d-inline">
					        	<a class="link-unstyled text-dark" href="<c:url value='/manager/myPage/onlineItem.do' />"><span th:text="${session.userSession.name} + '님'"></span></a>
					         </div>
				         </c:if>
				         <c:if test="${userSession.userId ne 'admin'}">
					         <div>
					         	<a class="link-unstyled text-dark" href="<c:url value='/user/myPage.do' />"><span><c:out value="${userSession.alias}"/></span> 님</a>
					         </div>
				         </c:if>
				     </div>
				     <!-- 로그아웃 -->
		         	<div class="col">
			         <form action="/user/signoff.do">
			          <button type="submit" class="btn btn-primary">
			            logout
			          </button>   
			         </form>
		          	</div> 
			     </c:if>   
	        </div>
      	</div>
      </div>
    </div>
  </nav> 
    <!-- Modal -->
	<div align="left" class="modal fade" id="modalForm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="exampleModalLabel">자꾸다꾸 로그인</h5>
	                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	            </div>
	            <div class="modal-body">
	                <form action="/user/signon.do" method = "Post">
	                    <div class="mb-3">
	                        <label class="form-label">ID</label>
	                        <input type="text" class="form-control" id="userId" name="userId" placeholder="Id를 입력하세요" />
	                    </div>
	                    <div class="mb-3">
	                        <label class="form-label">Password</label>
	                        <input type="password" class="form-control" id="password" name="password" placeholder="패쓰워드를 입력하세요" />
	                    </div>
	                    <div class="mb-3 form-check">
	                        <input  type="checkbox" class="form-check-input" id="rememberMe" />
	                        <label class="form-check-label" for="rememberMe">Remember me</label>
	                    </div>
	                    <div class="modal-footer d-block">
	                        <p class="float-start">Not yet account <a href="/user/newAccount.do">Sign Up</a></p>
	                        <button type="submit" class="btn btn-warning float-end">로그인</button>
	                    </div>
	                </form>
	            </div>
	        </div>
	    </div>
	</div>
  </div>
  
 