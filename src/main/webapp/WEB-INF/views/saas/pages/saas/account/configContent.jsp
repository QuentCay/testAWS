<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Configuration du contenu de votre Amap</h1>

<form
	action="${pageContext.request.contextPath}/configuration/editContent/${configuration.id}"
	method="post" enctype="multipart/form-data">

	<div class="contentForm-content">
		<div class="contentForm-side side1">
			<div class="input-group">
				<label for="logo" class="input-group-label">Logo de votre AMAP</label> 
				<input type="file" class="input-img" id="logo" name="logo" accept="image/*" />
			</div>
			<div class="input-group">
				<label for="presentationImage" class="input-group-label">Image de présentation</label> 
				<input type="file" class="input-img" id="presentationImage" name="presentationImage" accept="image/*" />
			</div>
			<div class="input-group">
				<label for="presentationText" class="input-group-label">Texte de présentation</label>
				<textarea id="presentationText" name="presentationText" rows="8" cols="80">${configuration.presentationText}</textarea>
			</div>
		</div>
	</div>


	<div class="configForm-submit">
		<button class="btn btn-secondary mb-4" type="submit">Enregistrer</button>
	</div>
</form>

<c:if test="${not empty message}">
	<div class="alert alert-success">${message}</div>
</c:if>
