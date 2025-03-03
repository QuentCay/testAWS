<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>
	Bienvenue, <span>${username} </span>!
</h1>

<p>Vous bénéficiez du forfait ${subscription}.</p>

<c:if test="${!empty amap.name}">
<div>
	<div>
		<a href="http://localhost:8080/seve/${slug}" target="_blank">
			<button class="btn btn-secondary mt-5">Accéder au site web de mon AMAP</button>
		</a>
	</div>
	<div>
		<button class="btn btn-secondary my-5" id="openSignupModal">Créer un compte administrateur</button>
	</div>
</div>
</c:if>

<c:if test="${empty amap.name}">
	<div class="alert alert-warning my-5">
		 <a href="${pageContext.request.contextPath}/amap/info" style="color: #856404;">Complétez les informations de votre AMAP pour accéder à l'url de votre site web.</a> 
	</div>
</c:if>

<a href="http://localhost:8080/seve/logout" class="d-block d-lg-none" style="text-decoration: underline;">Se déconnecter
            </a>

<c:if test="${not empty successMessage}">
    <div class="alert alert-success">
        ${successMessage}
    </div>
</c:if>

<c:if test="${not empty sessionScope.formErrors}">
    <div class="error-messages mt-5 alert alert-danger alert-dismissible fade show" role="alert">
            <c:forEach var="error" items="${sessionScope.formErrors}">
                <p>${error.defaultMessage}</p>
            </c:forEach>
    </div>
    <c:remove var="formErrors" scope="session" />
</c:if>
<div id="signupModal" class="modal" style="display: none;">
    <div class="modal-content shadow">
        <span id="closeModal" class="close">&times;</span>
<div class="container mt-3">
	<h1 class="mb-4 text-center modal-title">Création d'un compte administrateur
	</h1>

	<form
		action="${pageContext.request.contextPath}/${slug}/individual/createAdmin"
		method="post" class="needs-validation" novalidate>

		<div class="mb-3">
			<label for="name" class="form-label">Nom</label> <input type="text"
				id="name" name="amapUser.name" class="form-control"
				value="${amapUser.name != null ? amapUser.name : ''}"
				required>
			<div class="invalid-feedback">Veuillez saisir un nom.</div>
		</div>

		<div class="mb-3">
			<label for="firstname" class="form-label">Prénom</label> <input
				type="text" id="firstname" name="amapUser.firstname"
				class="form-control"
				value="${amapUser.firstname != null ? amapUser.firstname : ''}"
				required>
			<div class="invalid-feedback">Veuillez saisir un prénom.</div>
		</div>

		<div class="mb-3">
			<label for="email" class="form-label">Email</label> <input
				type="email" id="email" name="amapUser.email" class="form-control"
				value="${amapUser.email != null ? amapUser.email : ''}"
				required>
			<div class="invalid-feedback">Veuillez saisir un email valide.</div>
		</div>

		<div class="mb-3">
			<label for="phone" class="form-label">Numéro de téléphone</label> <input
				type="tel" id="phone" name="amapUser.phone" class="form-control"
				value="${amapUser.phone != null ? amapUser.phone : ''}"
				required>
			<div class="invalid-feedback">Veuillez saisir un numéro de
				téléphone valide.</div>
		</div>

		<div class="mb-3">
			<label for="password" class="form-label">Mot de passe</label> <input
				type="password" id="password" name="amapUser.password"
				class="form-control"
				value="${amapUser.password != null ? amapUser.password : ''}"
				required>
			<div class="invalid-feedback">Veuillez saisir un mot de passe.</div>
		</div>


		<div class="d-flex justify-content-center mt-5 mb-3">
			<a href="${pageContext.request.contextPath}/${slug}"
				class="btn btn-b btn-profile mx-3">Annuler</a>
			<button type="submit" class="btn btn-secondary mx-3">Enregistrer</button>
		</div>
	</form>
</div>
    </div>
</div>
