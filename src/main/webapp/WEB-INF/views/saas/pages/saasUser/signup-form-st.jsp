<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h1 class="text-center">Commencez aujourd'hui avec Standard</h1>
<section class="signup-section">
	<div class="signup-container">
		<!-- Info -->
		<div class="signup-info">
			<p>Démarrez dès aujourd'hui avec le forfait Standard. Aucun
				logiciel à installer.</p>
			<div class="my-4">
				<div class="signup-bp">
					<div class="dot-container">
						<div class="dot bg-o"></div>
					</div>
					<p>Démarrez facilement et rapidement en quelques clics avec des
						outils prêts à l'emploi</p>
				</div>
				<div class="signup-bp">
					<div class="dot-container">
						<div class="dot bg-b"></div>
					</div>
					<p>Gagnez en efficacité et stimulez votre performance grâce à
						outils adaptés et des solutions concrètes</p>
				</div>
				<div class="signup-bp">
					<div class="dot-container">
						<div class="dot bg-g"></div>
					</div>
					<p>Dynamisez les ventes, renforcez le service client et accédez
						à une expérience unique, le tout sur une seule et même plateforme</p>
				</div>
			</div>
			<div class="contact-info">
				<p class="font-weight-bold">Des questions ? Nous sommes là pour
					vous aider !</p>
				<p class="mb-1">Échangez avec un expert : (+33) 4 48 26 55 32</p>
				<p>
					ou contactez-nous via notre <a href="${pageContext.request.contextPath}/saas/contact">formulaire de contact</a>
				</p>
			</div>
		</div>
		<!-- Form -->
		<div class="signup-form-container shadow">
			<p class="font-weight-bold pb-2">Remplissez le formulaire pour
				démarrer votre abonnement</p>
			<c:if test="${not empty errorMessages}">
				<div class="alert alert-danger">
					<ul>
						<c:forEach var="error" items="${errorMessages}">
							<li>${error}</li>
						</c:forEach>
					</ul>
				</div>
			</c:if>
			<div class="panel-body">
				<form:form action="saveSignUpStandard" cssClass="form-horizontal"
					method="post" modelAttribute="saasUser">

					<div class="form-group signup-field">
						<label for="firstname" class="control-label">Prénom</label>

						<div>
							<form:input path="firstname" cssClass="form-control"
								value="${saasUser.firstname}" required="true" />
							<form:errors path="firstname" cssClass="error" />

						</div>
					</div>
					<div class="form-group signup-field">
						<label for="name" class="control-label">Nom</label>
						<div>
							<form:input path="name" cssClass="form-control"
								value="${saasUser.name}" required="true" />
							<form:errors path="name" cssClass="error" />

						</div>
					</div>

					<div class="form-group signup-field">
						<label for="phone" class="control-label">Téléphone</label>
						<div>
							<form:input path="phone" cssClass="form-control"
								value="${saasUser.phone}" required="true" />
							<form:errors path="phone" cssClass="error" />

						</div>
					</div>

					<div class="form-group signup-field">
						<label for="email" class="control-label">Email</label>
						<div>
							<form:input path="email" cssClass="form-control"
								value="${saasUser.email}" required="true" />
							<form:errors path="email" cssClass="error" />

						</div>
					</div>

					<div class="form-group signup-field">
						<label for="password" class="control-label">Mot de passe</label>
						<div>
							<form:password path="password" cssClass="form-control"
								value="${saasUser.password}" required="true" />
							<form:errors path="password" cssClass="error" />

						</div>
					</div>

					<div class="form-group">
						<div id="btns" class="form-buttons text-center"">
							<form:button class="btn btn-b">Valider</form:button>
						</div>
					</div>

				</form:form>
			</div>
		</div>
	</div>
</section>
<section class="features pb-5">
	<h2>Fonctionnalités comprises avec Standard</h2>
	<div class="feature-container">
		<div class="feature-card shadow-b">
			<p class="package-name bg-b text-center py-4">STANDARD</p>
			<div class="feature-card-content pb-3">
				<ul class="pb-3">
					<li class="pb-3">Boutique e-commerce : Ajoutez une boutique en
						ligne pour proposer des produits supplémentaires à vos adhérents.</li>
					<li>Gestion des paniers maraîchers</li>
					<li>Gestion des adhésions et des cotisations</li>
					<li>Répertoire des producteur</li>
					<li>Traitement des commandes</li>

				</ul>
				<div>
					<div>
						<p class="pb-3">Idéal pour : Les AMAP souhaitant développer
							leur offre avec une boutique en ligne.</p>
						<p class="price font-weight-bold pb-3">Prix : 19,99 € / mois</p>
					</div>
				</div>
			</div>
		</div>
		<img class="shadow"
			src="${pageContext.request.contextPath}/resources/images/saas/productList.png"
			alt="" />
	</div>
</section>