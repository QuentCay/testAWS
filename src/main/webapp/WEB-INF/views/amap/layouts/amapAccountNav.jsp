<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/amap/main.css" />
</head>
<div class="secondary-nav">
	<ul>
		<li class="mb-4"><a
			href="${pageContext.request.contextPath}/${slug}/dashboard">Mes Informations</a></li>
		<li class="mb-4"><a
			href="${pageContext.request.contextPath}/${slug}/order/list">Mes
				Commandes</a></li>
				<c:if test="${amapUser.type == 'PRODUCER'}">
		<li class="mb-4">
				<a href="${pageContext.request.contextPath}/${slug}/myproducts">Mes Produits</a>
			</li>
			 </c:if>
			 <sec:authorize access="hasRole('AMAP_ADMIN') or hasRole('AMAP_SUPERVISOR')">
        <li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" id="Preview" href="#" role="button" aria-haspopup="true"
					aria-expanded="false"> Gestion Administrateur </a>
					<div class="dropdown-menu mt-0" aria-labelledby="Preview">
						<a class="dropdown-item" data-bs-toggle="collapse" data-bs-target=".navbar-collapse.show"
							onclick="window.location.href='${pageContext.request.contextPath}/${slug}/box/admin'">Paniers</a> 
							
						<c:if test="${showProducts}">	
							<a class="dropdown-item" href="#" data-bs-toggle="collapse" data-bs-target=".navbar-collapse.show"
								onclick="window.location.href='${pageContext.request.contextPath}/${slug}/product/admin'">Produits</a>
						</c:if>
        				
        				<c:if test="${showActivities}">
							<a class="dropdown-item" href="#" data-bs-toggle="collapse" data-bs-target=".navbar-collapse.show" 
								onclick="window.location.href='${pageContext.request.contextPath}/${slug}/activity/admin'">Ateliers</a>
						</c:if>
					</div>
			</li>
			</sec:authorize>

	</ul>


</div>
