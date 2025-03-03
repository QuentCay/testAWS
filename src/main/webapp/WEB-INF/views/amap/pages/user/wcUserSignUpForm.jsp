<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container mt-3">
    <h1 class="mb-4 text-center">${amapWorksComitteeUser.id != null ? 'Modifier' : 'Création du compte'}</h1>

    <form action="${pageContext.request.contextPath}/${slug}/works-comittee/${amapWorksComitteeUser.id != null ? 'update' : 'signup'}"
          method="post" class="needs-validation" novalidate>
        
        <input type="hidden" name="id" value="${amapWorksComitteeUser.id}" />

        <c:if test="${not empty signupError}">
            <div class="alert alert-danger" role="alert">
                ${signupError}
            </div>
        </c:if>

        <form:errors path="*" cssClass="alert alert-danger" element="div" />

        <div class="d-flex flex-column align-items-center">
            <div class="mb-3 w-50">
                <label for="name" class="form-label"><strong>Nom :</strong></label>
                <input type="text" id="name" name="amapUser.name" class="form-control"
                       value="${amapWorksComitteeUser.amapUser.name}" placeholder="Entrez votre nom" required>
                <form:errors path="amapUser.name" cssClass="text-danger" />
                <div class="invalid-feedback">Veuillez saisir un nom.</div>
            </div>

            <div class="mb-3 w-50">
                <label for="firstname" class="form-label"><strong>Prénom :</strong></label>
                <input type="text" id="firstname" name="amapUser.firstname" class="form-control"
                       value="${amapWorksComitteeUser.amapUser.firstname}" placeholder="Entrez votre prénom" required>
                <form:errors path="amapUser.firstname" cssClass="text-danger" />
                <div class="invalid-feedback">Veuillez saisir un prénom.</div>
            </div>

            <div class="mb-3 w-50">
                <label for="email" class="form-label"><strong>Email :</strong></label>
                <input type="email" id="email" name="amapUser.email" class="form-control"
                       value="${amapWorksComitteeUser.amapUser.email}" placeholder="Entrez votre email" required>
                <form:errors path="amapUser.email" cssClass="text-danger" />
                <div class="invalid-feedback">Veuillez saisir un email valide.</div>
            </div>

            <div class="mb-3 w-50">
                <label for="phone" class="form-label"><strong>Numéro de téléphone :</strong></label>
                <input type="tel" id="phone" name="amapUser.phone" class="form-control"
                       value="${amapWorksComitteeUser.amapUser.phone}" placeholder="Entrez votre numéro de téléphone" required>
                <form:errors path="amapUser.phone" cssClass="text-danger" />
                <div class="invalid-feedback">Veuillez saisir un numéro de téléphone valide.</div>
            </div>
			
			<c:if test="${amapWorksComitteeUser.id == null}">
            <div class="mb-3 w-50">
                <label for="password" class="form-label"><strong>Mot de passe :</strong></label>
                <input type="password" id="password" name="amapUser.password" class="form-control"
                       value="" placeholder="Entrez votre mot de passe" required>
                <form:errors path="amapUser.password" cssClass="text-danger" />
                <div class="invalid-feedback">Veuillez saisir un mot de passe.</div>
            </div>
			</c:if>

            <div class="mb-3 w-50">
                <label for="companyName" class="form-label"><strong>Nom de l'entreprise :</strong></label>
                <input type="text" id="companyName" name="companyName" class="form-control"
                       value="${amapWorksComitteeUser.companyName}" placeholder="Entrez le nom de votre entreprise" required>
                <form:errors path="companyName" cssClass="text-danger" />
                <div class="invalid-feedback">Veuillez saisir un nom d'entreprise.</div>
            </div>

            <div class="mb-3 w-50">
                <label for="siret" class="form-label"><strong>Numéro SIRET :</strong></label>
                <input type="text" id="siret" name="siret" class="form-control"
                       value="${amapWorksComitteeUser.siret}" placeholder="Entrez votre numéro SIRET" required>
                <form:errors path="siret" cssClass="text-danger" />
                <div class="invalid-feedback">Veuillez saisir un numéro SIRET valide.</div>
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
