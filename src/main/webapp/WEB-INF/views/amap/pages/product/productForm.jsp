<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container mt-3">
	<h1 class="mb-4 text-center">${product.id != null ? 'Modifier' : 'Ajouter'}
		un produit</h1>

	<c:choose>
		<c:when test="${product.id != null}">
			<form
				action="${pageContext.request.contextPath}/${slug}/product/edit/${product.id}"
				method="post" class="needs-validation" novalidate
				enctype="multipart/form-data">
		</c:when>
		<c:otherwise>
			<form action="${pageContext.request.contextPath}/${slug}/product/add"
				method="post" class="needs-validation" novalidate
				enctype="multipart/form-data">
		</c:otherwise>
	</c:choose>

	<div class="row">
		<div class="col-md-6">
			<div class="mb-3">
				<label for="name" class="form-label">Nom :</label> <input
					type="text" id="name" name="name" class="form-control"
					value="${product.name}" required>
				<div class="invalid-feedback">Veuillez saisir un nom.</div>
			</div>

			<div class="mb-4">
				<label for="description" class="form-label">Description :</label>
				<textarea id="description" name="description" class="form-control"
					rows="4" required>${product.description}</textarea>
				<div class="invalid-feedback">Veuillez saisir une description.</div>
			</div>

			<div class="mb-3">
				<label for="category" class="form-label">Catégorie :</label> <select
					id="category" name="category" class="form-select" required>
					<option value="" selected disabled>Choisissez une
						catégorie</option>
					<c:forEach var="category" items="${categories}">
						<option value="${category}"
							${category == product.category ? 'selected' : ''}>${category.displayName}</option>
					</c:forEach>
				</select>
				<div class="invalid-feedback">Veuillez choisir une catégorie.</div>
			</div>

			<div class="mb-3">
				<label for="image">Aperçu de l'image : </label>
				<c:if test="${product.imageData != null}">
					<img
						src="${pageContext.request.contextPath}/${slug}/product/image/${product.id}"
						alt="Aperçu de l'image" class="img-thumbnail mb-3" style="width: 50px; height: auto;">
				</c:if>
				</div>
				<div class="mb-3">
				<input type="file" id="image" name="image" accept="image/*" />
			</div>
		</div>

		<div class="col-md-1"></div>

		<div class="col-md-3">
			<div class="mb-3">
				<label for="availableDate" class="form-label">Date de
					disponibilité :</label> <input type="date" id="availableDate"
					name="availableDate" class="form-control"
					value="${product.availableDate}" required>
				<div class="invalid-feedback">Veuillez choisir une date de
					disponibilité.</div>
			</div>

			<div class="mb-3">
				<label for="purchaseDeadlineDate" class="form-label">Date
					limite d'achat :</label> <input type="date" id="purchaseDeadlineDate"
					name="purchaseDeadlineDate" class="form-control"
					value="${product.purchaseDeadlineDate}" required>
				<div class="invalid-feedback">Veuillez choisir une date limite
					d'achat.</div>
			</div>

			<div class="mb-3">
				<label for="price" class="form-label">Prix :</label> <input
					type="number" id="price" name="price" class="form-control"
					value="${product.price}" step="0.01" min="0.01" required>
				<div class="invalid-feedback">Veuillez saisir un prix valide.</div>
			</div>


			<div class="mb-3">
				<label for="stock" class="form-label">Stock :</label> <input
					type="number" id="stock" name="stock" class="form-control"
					value="${product.stock}" min="1" required>
				<div class="invalid-feedback">Veuillez saisir une quantité en
					stock valide.</div>
			</div>

		</div>
	</div>

	<div class="d-flex justify-content-center mt-4 mb-5">
		<a href="${pageContext.request.contextPath}/${slug}/product/admin"
			class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Annuler</a>

		<button type="submit" class="btn btn-secondary" style=" margin-left: 10px; color: black; background-color: var(--secondary-color)">Enregistrer</button>
	</div>
	</form>
</div>


<script
	src="${pageContext.request.contextPath}/resources/js/formValidation.js"></script>

