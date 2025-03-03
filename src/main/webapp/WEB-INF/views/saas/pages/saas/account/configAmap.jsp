<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <h1>Informations de votre AMAP</h1>

	   <form id="amapForm" action="${pageContext.request.contextPath}/amap/editAmap/${amap.id}" method="post">

        <p>
            <label for="name">Nom</label>
            <input type="text" id="name" name="name" value="${amap.name}" required>
        </p>
        <p>
            <label for="address">Adresse</label>
            <input type="text" id="address" name="address" value="${amap.address}" required>
        </p>
        <p>
            <label for="siret">Numéro Siret</label>
            <input id="siret" name="siret" value="${amap.siret}" required>
        </p>
        <p class="mt-4">
            <label for="membershipFee">Montant de la cotisation</label>
            <input id="membershipFee" name="membershipFee" value="${amap.membershipFee}" required>
        </p>
        <div class="configForm-submit">
			<button class="btn btn-secondary" type="submit">Enregistrer</button>
		</div>
    </form>
    

<c:if test="${not empty message}">
    <div class="alert alert-success  mt-4">
        ${message}
    </div>
</c:if>
