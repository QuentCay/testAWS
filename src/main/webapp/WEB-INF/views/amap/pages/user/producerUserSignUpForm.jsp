<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container mt-5">
    <h1 class="mb-4 text-center">${amapProducerUser.id != null ? 'Modifier' : 'Création du compte'}</h1>

    <form
        action="${pageContext.request.contextPath}/${slug}/producer/${amapProducerUser.id != null ? 'update' : 'signup'}"
        method="post" class="needs-validation" novalidate>
        
        <input type="hidden" name="id" value="${amapProducerUser.id}" />

        <c:if test="${not empty signupError}">
            <div class="alert alert-danger" role="alert">
                ${signupError}
            </div>
        </c:if>

        <form:errors path="*" cssClass="alert alert-danger" element="div" />

        <div class="d-flex flex-column align-items-center ">
            <div class="mb-3 w-50">
                <label for="name" class="form-label"><strong>Nom :</strong></label>
                <input type="text" id="name" name="amapUser.name" class="form-control"
                       value="${amapUser.name != null ? amapUser.name : ''}" placeholder="Entrez votre nom" required>
                <form:errors path="amapUser.name" cssClass="text-danger" />
                <div class="invalid-feedback">Veuillez saisir un nom.</div>
            </div>

            <div class="mb-3 w-50">
                <label for="firstname" class="form-label"><strong>Prénom :</strong></label>
                <input type="text" id="firstname" name="amapUser.firstname" class="form-control"
                       value="${amapUser.firstname != null ? amapUser.firstname : ''}" placeholder="Entrez votre prénom" required>
                <form:errors path="amapUser.firstname" cssClass="text-danger" />
                <div class="invalid-feedback">Veuillez saisir un prénom.</div>
            </div>

            <div class="mb-3 w-50">
                <label for="email" class="form-label"><strong>Email :</strong></label>
                <input type="email" id="email" name="amapUser.email" class="form-control"
                       value="${amapUser.email != null ? amapUser.email : ''}" placeholder="Entrez votre email" required>
                <form:errors path="amapUser.email" cssClass="text-danger" />
                <div class="invalid-feedback">Veuillez saisir un email valide.</div>
            </div>

            <div class="mb-3 w-50">
                <label for="phone" class="form-label"><strong>Numéro de téléphone :</strong></label>
                <input type="tel" id="phone" name="amapUser.phone" class="form-control"
                       value="${amapUser.phone != null ? amapUser.phone : ''}" placeholder="Entrez votre numéro de téléphone" required>
                <form:errors path="amapUser.phone" cssClass="text-danger" />
                <div class="invalid-feedback">Veuillez saisir un numéro de téléphone valide.</div>
            </div>
			
			<c:if test="${amapProducerUser.id == null}">
            <div class="mb-3 w-50">
                <label for="password" class="form-label"><strong>Mot de passe :</strong></label>
                <input type="password" id="password" name="amapUser.password" class="form-control"
                       value="${amapUser.password != null ? amapUser.password : ''}" placeholder="Entrez votre mot de passe" required>
                <form:errors path="amapUser.password" cssClass="text-danger" />
                <div class="invalid-feedback">Veuillez saisir un mot de passe.</div>
            </div>
			</c:if>
			
            <div class="mb-3 w-50">
                <label for="rib" class="form-label"><strong>RIB :</strong></label>
                <input type="text" id="rib" name="rib" class="form-control"
                       value="${amapProducerUser.rib != null ? amapProducerUser.rib : ''}" placeholder="Entrez votre RIB">
                <form:errors path="rib" cssClass="text-danger" />
                <div class="invalid-feedback">Veuillez entrer un RIB valide.</div>
            </div>

            <div class="mb-3 w-50">
                <label for="address" class="form-label"><strong>Adresse :</strong></label>
                <textarea id="address" name="address" class="form-control" rows="3"
                          placeholder="Entrez votre adresse">${amapProducerUser.address != null ? amapProducerUser.address : ''}</textarea>
                <form:errors path="address" cssClass="text-danger" />
                <div class="invalid-feedback">Veuillez entrer une adresse valide.</div>
            </div>

            <div class="d-flex justify-content-center mt-4 mb-5">
                <a href="${pageContext.request.contextPath}/${slug}" class="btn btn-secondary mx-3"
                   style="color: black; background-color: var(--secondary-color)">Annuler</a>
                <button type="submit" class="btn btn-secondary mx-3"
                        style="color: black; background-color: var(--secondary-color)">Enregistrer</button>
            </div>
        </div>
    </form>
</div>
