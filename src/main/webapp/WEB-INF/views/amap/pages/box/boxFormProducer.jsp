<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container mt-3">
	<h1 class="mb-4 text-center">${box.id != null ? 'Modifier' : 'Ajouter'}
		un panier</h1>

	<c:choose>
		<c:when test="${box.id != null}">
			<form
				action="${pageContext.request.contextPath}/${slug}/box/editProd/${box.id}"
				method="post" class="needs-validation" novalidate enctype="multipart/form-data">
		</c:when>
		<c:otherwise>
			<form action="${pageContext.request.contextPath}/${slug}/box/addProd"
				method="post" class="needs-validation" novalidate enctype="multipart/form-data">
		</c:otherwise>
	</c:choose>

	<div class="row">
		<div class="col-md-6">
			<div class="mb-3">
				<label for="name" class="form-label">Nom :</label> <input
					type="text" id="name" name="name" class="form-control"
					value="${box.name}" required>
				<div class="invalid-feedback">Veuillez saisir un nom.</div>
			</div>

			<div class="mb-4">
				<label for="description" class="form-label">Description :</label>
				<textarea id="description" name="description" class="form-control"
					rows="4" required>${box.description}</textarea>
				<div class="invalid-feedback">Veuillez saisir une description.</div>
			</div>

			<div class="mb-3">
				<label for="category" class="form-label">Catégorie :</label> <select
					id="category" name="category" class="form-select" required>
					<option value="" selected disabled>Choisissez une
						catégorie</option>
					<c:forEach var="category" items="${categories}">
						<option value="${category}"
							${category == box.category ? 'selected' : ''}>${category.displayName}</option>
					</c:forEach>
				</select>
				<div class="invalid-feedback">Veuillez choisir une catégorie.</div>
			</div>

			<div class="mb-3">
				<label for="frequency" class="form-label">Fréquence :</label> <select
					id="frequency" name="frequency" class="form-select" required>
					<option value="" selected disabled>Choisissez une
						fréquence</option>
					<c:forEach var="frequency" items="${frequencies}">
						<option value="${frequency}"
							${frequency == box.frequency ? 'selected' : ''}>${frequency.displayName}</option>
					</c:forEach>
				</select>
				<div class="invalid-feedback">Veuillez choisir une fréquence.</div>
			</div>
<div class="mb-3">
         <label for="image">Aperçu de l'image : </label>
         <c:if test="${box.imageData != null}">
            <img src="${pageContext.request.contextPath}/${slug}/box/image/${box.id}" alt="Aperçu de l'image" class="img-thumbnail mb-3" style="width: 50px; height: auto;">
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
					value="${box.availableDate}" required>
				<div class="invalid-feedback">Veuillez choisir une date de
					disponibilité.</div>
			</div>

			<div class="mb-3">
				<label for="purchaseDeadlineDate" class="form-label">Date
					limite d'achat :</label> <input type="date" id="purchaseDeadlineDate"
					name="purchaseDeadlineDate" class="form-control"
					value="${box.purchaseDeadlineDate}" required>
				<div class="invalid-feedback">Veuillez choisir une date limite
					d'achat.</div>
			</div>

			<div class="mb-3">
				<label for="price" class="form-label">Prix :</label> <input
					type="number" id="price" name="price" class="form-control"
					value="${box.price}" step="0.01" min="0.01" required>
				<div class="invalid-feedback">Veuillez saisir un prix valide.</div>
			</div>


			<div class="mb-3">
				<label for="stock" class="form-label">Stock :</label> <input
					type="number" id="stock" name="stock" class="form-control"
					value="${box.stock}" min="1" required>
				<div class="invalid-feedback">Veuillez saisir une quantité en
					stock valide.</div>
			</div>

		</div>

		 
	</div>

	<div class="d-flex justify-content-center mt-4 mb-5">
		<a href="${pageContext.request.contextPath}/${slug}/myproducts"
			class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Annuler</a>

		<button type="submit" class="btn btn-secondary" style=" margin-left: 10px; color: black; background-color: var(--secondary-color)">Enregistrer</button>
	</div>
	</form>
</div>


<script
	src="${pageContext.request.contextPath}/resources/js/formValidation.js"></script>