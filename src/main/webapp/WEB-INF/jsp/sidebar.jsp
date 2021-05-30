<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- side bar-->
	<div class="col-3 sidebar d-flex flex-column p-3 bg-light" style="width: 250px;">
		<a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
			<svg class="bi me-2" width="40" height="32">
				<use xlink:href="#bootstrap"></use>
			</svg>
			<span class="fs-4">Sidebar</span>
		</a>
		<hr>
	    <ul class="nav nav-pills flex-column mb-auto">
	        <li>
	            <a href="<c:url value='/item/viewCategory.do?categoryId=0&themeId=-1&device=-1&sortBy=1' />" class="nav-link link-dark">
	                <svg class="bi me-2" width="16" height="16">
	                    <use xlink:href="#speedometer2"></use>
	                </svg>
	                Diary
	            </a>
	        </li>
	        <li>
	            <a href="<c:url value='/item/viewCategory.do?categoryId=1&themeId=-1&device=-1&sortBy=1' />" class="nav-link link-dark">
	                <svg class="bi me-2" width="16" height="16">
	                    <use xlink:href="#table"></use>
	                </svg>
	                Font
	            </a>
	        </li>
	        <li>
	            <a href="<c:url value='/item/viewCategory.do?categoryId=2&themeId=-1&device=-1&sortBy=1' />" class="nav-link link-dark">
	                <svg class="bi me-2" width="16" height="16">
	                    <use xlink:href="#grid"></use>
	                </svg>
	                Wallpaper
	            </a>
	        </li>
	        <li>
	            <a href="<c:url value='/item/viewCategory.do?categoryId=3&themeId=-1&device=-1&sortBy=1' />" class="nav-link link-dark">
	                 <svg class="bi me-2" width="16" height="16">
	                     <use xlink:href="#people-circle"></use>
	                 </svg>
	                 Icon
	             </a>
	        </li>
	        <hr>
			<li><a href="<c:url value='/item/viewFundings.do?themeId=-1&sortBy=1'/>" class="nav-link link-dark">
					<svg class="bi me-2" width="16" height="16">
			                        <use xlink:href="#people-circle"></use>
			                    </svg> funding
				</a>
			</li>
	    </ul>
	</div>