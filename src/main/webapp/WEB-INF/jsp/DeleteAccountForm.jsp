<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div align="center">
<form:form modelAttribute="accountForm" method="post" >
  <form:errors cssClass="error" /> <br><br>
  
  <table id="account">
    <tr>
      <td>
        <h3><font color="darkgreen">회원 탈퇴</font></h3>
        <table class="n13">
          <tr>
            <td>비밀번호를 입력해주세요</td>
            <td>
              <form:password path="account.password" /> 
              <B><form:errors path="account.password" cssClass="error" /></B></td>
          </tr>
          
         </table>
         </td></tr></table>
         
         <br />
         <button  type="submit" id="submit">회원탈퇴</button>
		 <button  type="button">취소</button>
    </form:form>
        