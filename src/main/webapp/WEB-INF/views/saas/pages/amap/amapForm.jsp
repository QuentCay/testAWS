<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <h1>${amap.id != null ? 'Modifier' : 'Ajouter'} une AMAP</h1>
	<c:choose>
	    <c:when test="${amap.id != null}">
	        <form action="${pageContext.request.contextPath}/amap/edit/${amap.id}" method="post">
	    </c:when>
	    <c:otherwise>
	        <form action="${pageContext.request.contextPath}/amap/add" method="post">
	    </c:otherwise>
	</c:choose>
        <p>
            <label for="name">Nom :</label>
            <input type="text" id="name" name="name" value="${amap.name}" required>
        </p>
        <p>
            <label for="address">Adresse :</label>
            <input type="text" id="address" name="address" value="${amap.address}" required>
        </p>
        <p>
            <label for="siret">Siret :</label>
            <input id="siret" name="siret" value="${amap.siret}" required>
        </p>
        <p>
            <button type="submit">Enregistrer</button>
            <a href="${pageContext.request.contextPath}/amap">Annuler</a>
        </p>
    </form>
