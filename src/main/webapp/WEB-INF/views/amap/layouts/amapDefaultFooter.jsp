<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<footer class="bg-o text-center">
	<div class="footer-container">
		<div class="footer-content">
			<img
				src="${pageContext.request.contextPath}/configuration/logo/${amap.amapSpace.configuration.id}"
				alt="Logo de l'AMAP" />
		</div>
		<div class="footer-content">
			<h3>Coordonnées</h3>


			<p><strong>Adresse du bureau :</strong> ${amap.address}</p><br/>
			<p><strong>Email :</strong> ${amap.saasUser.email}</p><br/>
			<p><strong>Téléphone :</strong> ${amap.saasUser.phone}</p><br/>
			<p><strong>SIRET Association :</strong> ${amap.siret}</p>

		</div>
		
		<div class="footer-content">
			<h3>Navigation</h3>


			<a class="nav-link" data-bs-toggle="collapse" data-bs-target=".navbar-collapse.show" onclick="window.location.href='${pageContext.request.contextPath}/${amap.slug}'">Accueil</a>
			<a class="nav-link" data-bs-toggle="collapse" data-bs-target=".navbar-collapse.show"
				onclick="window.location.href='${pageContext.request.contextPath}/${amap.slug}/box'">Nos paniers maraîchers</a>
			<c:if test="${showProducts}">
			<a class="nav-link" data-bs-toggle="collapse" data-bs-target=".navbar-collapse.show"
				onclick="window.location.href='${pageContext.request.contextPath}/${amap.slug}/product'">Nos produits</a>
			</c:if>
        				
			<c:if test="${showActivities}">
			<a class="nav-link" data-bs-toggle="collapse" data-bs-target=".navbar-collapse.show"
				onclick="window.location.href='${pageContext.request.contextPath}/${amap.slug}/activity'">Nos ateliers</a>
			</c:if>
		</div>
		
	</div>
	<div class="footer-container">
	<p class="cr mt-3">© 2025 - Tous droits réservés</p>
	</div>
</footer>