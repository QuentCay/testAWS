<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Page d'accueil des devs</h1>
<ul>
	<li><a href="${pageContext.request.contextPath}/saas">Saas</a></li>
</ul>

<h3 class="mt-5">AMAPs existantes</h3>
<table border="1" class="mb-5">
	<thead>
		<tr>
			<th>Nom</th>
			<th>Lien</th>
			<c:forEach var="amap" items="${amaps}">
				<tr>
					<td>${amap.name}</td>
					<td><a href="http://localhost:8080/seve/${amap.slug}"
						target="_blank">http://localhost:8080/seve/${amap.slug}</a></td>
				</tr>
			</c:forEach>
	</tbody>
</table>
