<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/amap/login.css" />
</head>

<div class="login-main">
	<div class="login-form-container shadow mb-3">
		<p class="font-weight-bold pb-4">Connectez-vous pour accéder à
			votre compte</p>
		<div class="panel-body">
			<form method="POST" action="${pageContext.request.contextPath}/${slug}/login"
				class="form-horizontal">
				<div class="form-group login-field">
					<label for="username" class="control-label">Adresse email</label>
					<div>
						<input type="text" id="username" name="username"
							class="form-control" required>
					</div>
				</div>

				<div class="form-group login-field mt-4 mb-2">
					<label for="password" class="control-label">Mot de passe</label>
					<div>
						<input type="password" id="password" name="password"
							class="form-control" required>
					</div>
				</div>

				<div style="height: 20px; text-align: center; color: red; font-size: 14px">
					<c:if test="${not empty error}">${error}</c:if>
				</div>

				<!-- Jeton CSRF -->
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

				<div class="form-group">
					<div id="btns" class="form-buttons text-center">
						<button type="submit" class="btn btn-b">Se connecter</button>
					</div>
				</div>
			</form>
		</div>

	</div>
</div>
