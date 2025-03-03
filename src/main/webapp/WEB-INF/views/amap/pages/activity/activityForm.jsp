<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container mt-3">
	<h1 class="mb-4 text-center">${activity.id != null ? 'Modifier' : 'Ajouter'}
		un atelier</h1>

	<c:choose>
		<c:when test="${activity.id != null}">
			<form
				action="${pageContext.request.contextPath}/${slug}/activity/edit/${activity.id}"
				method="post" class="needs-validation" novalidate enctype="multipart/form-data">
		</c:when>
		<c:otherwise>
			<form
				action="${pageContext.request.contextPath}/${slug}/activity/add"
				method="post" class="needs-validation" novalidate enctype="multipart/form-data">
		</c:otherwise>
	</c:choose>

	<div class="row">
		<div class="col-md-6">
			<div class="mb-3">
				<label for="name" class="form-label">Nom :</label> <input
					type="text" id="name" name="name" class="form-control"
					value="${activity.name}" required>
				<div class="invalid-feedback">Veuillez saisir un nom.</div>
			</div>

			<div class="mb-4">
				<label for="description" class="form-label">Description :</label>
				<textarea id="description" name="description" class="form-control"
					rows="4" required>${activity.description}</textarea>
				<div class="invalid-feedback">Veuillez saisir une description.</div>
			</div>

			<div class="mb-4">
				<label for="place" class="form-label">Lieu de l'atelier :</label>
				<textarea id="place" name="place" class="form-control" rows="3"
					required>${activity.place}</textarea>
				<div class="invalid-feedback">Veuillez saisir l'adresse de
					l'atelier.</div>
			</div>

			<div class="mb-3">
				<label for="price" class="form-label">Prix :</label> <input
					type="number" id="price" name="price" class="form-control"
					value="${activity.price}" min="1" required>
				<div class="invalid-feedback">Veuillez saisir un prix valide.</div>
			</div>

			<div class="mb-3">
				<label for="image">Aperçu de l'image : </label>
				<c:if test="${activity.imageData != null}">
					<img
						src="${pageContext.request.contextPath}/${slug}/activity/image/${activity.id}"
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
				<label for="date" class="form-label">Date de l'atelier :</label> <input
					type="date" id="date" name="date" class="form-control"
					value="${activity.date}" required>
				<div class="invalid-feedback">Veuillez choisir la date de
					l'atelier.</div>
			</div>


			<div class="mb-3">
				<label for="startTime" class="form-label">Heure de début :</label> <input
					type="time" id="startTime" name="startTime" class="form-control"
					value="${activity.startTime}" required>
				<div class="invalid-feedback">Veuillez choisir l'heure de
					début de l'atelier.</div>
			</div>

			<div class="mb-3">
				<label for="endTime" class="form-label">Heure de fin :</label> <input
					type="time" id="endTime" name="endTime" class="form-control"
					value="${activity.endTime}" required>
				<div class="invalid-feedback">Veuillez choisir l'heure de fin
					de l'atelier.</div>
			</div>

			<div class="mb-3">
				<label for="purchaseDeadlineDate" class="form-label">Date
					limite d'achat :</label> <input type="date" id="purchaseDeadlineDate"
					name="purchaseDeadlineDate" class="form-control"
					value="${activity.purchaseDeadlineDate}" required>
				<div class="invalid-feedback">Veuillez choisir une date limite
					d'achat.</div>
			</div>




			<div class="mb-3">
				<label for="availableSpace" class="form-label">Nombre de
					places disponibles :</label> <input type="number" id="availableSpace"
					name="availableSpace" class="form-control"
					value="${activity.availableSpace}" min="1" required>
				<div class="invalid-feedback">Veuillez saisir un nombre de
					place valide.</div>
			</div>

		</div>
	</div>

	<div class="d-flex justify-content-center mt-4 mb-5">
		<a href="${pageContext.request.contextPath}/${slug}/activity/admin"
			class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Annuler</a>

		<button type="submit" class="btn btn-secondary" style=" margin-left: 10px; color: black; background-color: var(--secondary-color)">Enregistrer</button>
	</div>
	</form>
</div>


<script
	src="${pageContext.request.contextPath}/resources/js/formValidation.js"></script>




