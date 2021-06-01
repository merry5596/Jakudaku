<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="navbar.jsp" %>
<%@ include file="sidebar.jsp" %>
<div class="col-7 base">
<div align="center">
<form:form modelAttribute="reviewForm" method="post" >
  <form:errors cssClass="error" /> <br><br>
  
  <table id="review">
    <tr>
      <td>
        <h3><font color="darkgreen">Review</font></h3>
        <table>
        	<tr>
            <td> <img src="${item.thumbnail1}" style="width:100px; height:100px;"/> </td>
            </tr>
            <tr>
            <td> 상품명 : ${item.name}</td>
            </tr>
        </table>
        <br>
        <table class="n13">
          <tr>
            <td>평점:</td>
            <td>
              <form:input path="review.rate" /> 
              <B><form:errors path="review.rate" cssClass="error" /></B></td>
          </tr>
          <tr>
            <td>내용:</td>
            <td>
              <form:input path="review.content" /> 
              <B><form:errors path="review.content" cssClass="error" /></B></td>
          </tr>
        </table>
         
         </table>
         
         <br />
         
    <c:if test="${reviewForm.newReview}">
    	<input type="image" src="../images/button_submit.gif" name="submit" value="리뷰작성" />
    </c:if>    
    <c:if test="${!reviewForm.newReview}">
    	<input type="image" src="../images/button_submit.gif" name="submit" value="리뷰수정" />
    	<button type="button"  onClick="location.href='<c:url value="/review/removeReview.do"><c:param name="reviewId" value="${reviewForm.review.reviewId}"/></c:url>'">리뷰삭제</button>
    </c:if>
    </form:form>
    </div>
    </div>
<%@ include file="bottom.jsp" %>