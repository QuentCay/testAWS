<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/amap/main.css" />
</head>

<nav id="navbar" class="navbar navbar-expand-sm navbar-light">
	<div class="nav-collapse">
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#nav-content" aria-controls="nav-content"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<img class="nav-logo"
			src="${pageContext.request.contextPath}/configuration/logo/${amap.amapSpace.configuration.id}"
			alt="Logo de l'AMAP" />
	</div>
		
	<div class="collapse navbar-collapse" id="nav-content">
		<ul class="navbar-nav ml-auto">
			<li class="nav-item">
				<a class="nav-link" data-bs-toggle="collapse" data-bs-target=".navbar-collapse.show"
					onclick="window.location.href='${pageContext.request.contextPath}/${slug}'"><strong>Accueil</strong></a>
			</li>
			
			<li class="nav-item">
				<a class="nav-link" data-bs-toggle="collapse" data-bs-target=".navbar-collapse.show"
					onclick="window.location.href='${pageContext.request.contextPath}/${slug}/box'"><strong>Nos paniers maraîchers</strong></a>
			</li>		
				 
			<c:if test="${showProducts}">
            	<li class="nav-item">
                	<a class="nav-link" data-bs-toggle="collapse" data-bs-target=".navbar-collapse.show"
                       	onclick="window.location.href='${pageContext.request.contextPath}/${slug}/product'"><strong>Nos produits</strong></a>
            	</li>
        	</c:if>
        	
        	<c:if test="${showActivities}">
            	<li class="nav-item">
                	<a class="nav-link" data-bs-toggle="collapse" data-bs-target=".navbar-collapse.show"
                       onclick="window.location.href='${pageContext.request.contextPath}/${slug}/activity'"><strong>Nos ateliers</strong></a>
            	</li>
        	</c:if>
			
				<li class="nav-item">
                	<a class="nav-link" data-bs-toggle="collapse" data-bs-target=".navbar-collapse.show"
                       onclick="window.location.href='${pageContext.request.contextPath}/${slug}/contact'"><strong>Contactez nous</strong></a>
            	</li>
			
		</ul>
		
		<ul class="navbar-nav ml-auto">
		<sec:authorize access="hasRole('AMAP_ADMIN') or hasRole('AMAP_USER') or hasRole('AMAP_SUPERVISOR')">
			<li class="nav-item">
				<a class="nav-link" href="index.html" data-bs-toggle="collapse" data-bs-target=".navbar-collapse.show"
					onclick="window.location.href='${pageContext.request.contextPath}/${slug}/cart'">
					<img src="${pageContext.request.contextPath}/resources/images/amap/cart.png" alt="Panier" style="width: 20px; height: 20px;">
				</a>
			</li>
			</sec:authorize>
			
		        <sec:authorize access="isAnonymous() or hasRole('SAAS_CUSTOM') or hasRole('SAAS_ADMIN')">
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.request.contextPath}/${slug}/login" data-bs-toggle="" data-bs-target=".navbar-collapse.show"><strong>Se connecter</strong></a>
					</li>		
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.request.contextPath}/${slug}/signup" data-bs-toggle="" data-bs-target=".navbar-collapse.show"><strong>Créer un compte</strong></a>
					</li>		
        		</sec:authorize>
          		<sec:authorize access="hasRole('AMAP_ADMIN') or hasRole('AMAP_USER') or hasRole('AMAP_SUPERVISOR')">
          		<li>
            		<a class="nav-link" href="${pageContext.request.contextPath}/${slug}/dashboard" data-bs-toggle="" data-bs-target=".navbar-collapse.show"><strong>Mon Compte</strong></a>
            		</li>
            		<li>
            		<a class="nav-link" href="${pageContext.request.contextPath}/${slug}/logout" data-bs-toggle="" data-bs-target=".navbar-collapse.show"><strong>Se deconnecter</strong></a>
            		</li>
        		</sec:authorize>
		</ul>
		
	</div>
</nav>

