<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="navbar.jsp"%>
<%@ include file="sidebar.jsp"%>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/components/button.min.css"
	integrity="sha512-OD0ScwZAE5PCg4nATXnm8pdWi0Uk0Pp2XFsFz1xbZ7xcXvapzjvcxxHPeTZKfMjvlwwl4sGOvgJghWF2GRZZDw=="
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/components/form.min.css"
	integrity="sha512-GqdyivmcmveCy9pRf2yTD/TPYXVmbPFDojvy15i3xuLLy9nkgNhDRMk6+Dt/uYDdnItFUFF7WqW7GoGv+gPX+A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/components/input.min.css"
	integrity="sha512-5i24U/IyvjscOlHec06pTtT16Oq70uKtnjhrZ2oez7k/qBVOdpNHPK2+SVkuxni95v1x5VGOfXYQZxm4oZcHdQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/components/grid.min.css"
	integrity="sha512-RtktBL0Hhw9K2e2rGLZGadK8cF6Q0wKxRUkfh4pJszwnarmh3GbqHCiLm3UZFA1dRiFqtPkVrcby0hXWsqoDNA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<div class="col-7 base">
	<div align="center">
		<form:form modelAttribute="reviewForm" method="post" class="ui form">
			<form:errors cssClass="error" />
			<br>
			<br>

			<table id="review" style="width: auto;">
				<tr>
					<td>
						<h3>
							<font color="darkgreen">Review</font>
						</h3>
						<table>
							<tr>
								<td><img src="${item.thumbnail1Path}"
									style="width: 250px; height: 250px;" /></td>
							</tr>
							<tr>
								<td style="text-align: center;">[${item.name}]</td>
							</tr>
						</table> <br>
						<table class="n13">
							<tr>
								<td>평점</td>
								<td><form:input path="review.rate" /> <B><form:errors
											path="review.rate" cssClass="error" /></B></td>
							</tr>
							<tr>
								<td>내용</td>
								<td><form:input path="review.content" /> <B><form:errors
											path="review.content" cssClass="error" /></B></td>
							</tr>
						</table> <br> <c:if test="${reviewForm.newReview}">
							<input type="submit" class="ui red button" style="width: 100%;"
								name="submit" value="리뷰작성" />
						</c:if> <c:if test="${!reviewForm.newReview}">
							<div class="ui center aligned grid">
								<div class="eight wide column">
									<input type="submit" class="ui red button" style="width: 100%;"
										name="submit" value="리뷰수정" />
								</div>

								<div class="eight wide column">
									<button type="button" class="ui red button"
										onClick="location.href='<c:url value="/review/removeReview.do"><c:param name="reviewId" value="${reviewForm.review.reviewId}"/></c:url>'">리뷰삭제</button>

								</div>
							</div>
						</c:if>
			</table>

			<br />


		</form:form>

	</div>
</div>


<script src="https://starratingjs.netlify.app/index.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/components/form.min.js"
	integrity="sha512-VM6WNFlVLFwKXphssthAXJpG3cKWUK3G4edfsydLITA4iSeZmvJ+2gKBrR6qYkt/3A/I8hDHnAuIBz7xtfVtOg=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
	//     var properties1=[
	//         {"rating":"4.7", "maxRating":"5", "minRating":"0.5", "readOnly":"no", "starImage":"https://starratingjs.netlify.app/star.png", "backgroundStarImage":"https://starratingjs.netlify.app/backgroundStar.png", "starSize":"16", "step":"0.5"},
	//     ];

	//     rateSystem($('className'), properties1, function(rating, ratingTargetElement){  ratingTargetElement.parentElement.parentElement.getElementsByClassName("ratingHolder")[0].innerHTML = rating; });
</script>

<%@ include file="bottom.jsp"%>