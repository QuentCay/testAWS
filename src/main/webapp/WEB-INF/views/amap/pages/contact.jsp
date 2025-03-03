<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container mt-3">
    <h1 class="mb-4 text-center">Contactez-nous</h1>

    <form id="contactForm" class="needs-validation" novalidate>
        <div class="d-flex flex-column align-items-center">

            <div class="mb-3 w-50">
                <label for="name" class="form-label"><strong>Nom :</strong></label>
                <input type="text" id="name" name="name" class="form-control"
                       placeholder="Entrez votre nom" required>
                <div class="invalid-feedback">Veuillez saisir un nom.</div>
            </div>

            <div class="mb-3 w-50">
                <label for="email" class="form-label"><strong>Email :</strong></label>
                <input type="email" id="email" name="email" class="form-control"
                       placeholder="Entrez votre email" required>
                <div class="invalid-feedback">Veuillez saisir un email valide.</div>
            </div>

            <div class="mb-3 w-50">
                <label for="subject" class="form-label"><strong>Sujet :</strong></label>
                <input type="text" id="subject" name="subject" class="form-control"
                       placeholder="Entrez le sujet de votre demande" required>
                <div class="invalid-feedback">Veuillez saisir un sujet.</div>
            </div>

            <div class="mb-3 w-50">
                <label for="message" class="form-label"><strong>Message :</strong></label>
                <textarea id="message" name="message" class="form-control"
                          placeholder="Entrez votre message" rows="5" required></textarea>
                <div class="invalid-feedback">Veuillez saisir un message.</div>
            </div>
        </div>

        <div class="d-flex justify-content-center mt-4 mb-5">
            <button type="button" id="submitButton" class="btn btn-secondary mx-3"
                    style="color: black; background-color: var(--secondary-color)">Envoyer</button>
        </div>

        <div id="successMessage" class="alert alert-success text-center d-none" role="alert">
            Votre demande a bien été envoyée !
        </div>
    </form>
</div>
