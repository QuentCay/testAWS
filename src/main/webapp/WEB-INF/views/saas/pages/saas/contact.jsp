<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div>
	<div class="contact-page mt-5">
		<div class="contact-info">
			<div id="map"></div>
			<h3>Nos coordonnées</h3>
			<p>3 boulevard Michelet</p>
			<p>13008 Marseille, France</p>
			<p>Tél : (+33) 4 48 26 55 32</p>
			<p>Email : contact@seve.com</p>
		</div>

		<div class="contact-form shadow">
			<form action="${pageContext.request.contextPath}/contact"
				method="post">
				<div class="contact-form-input">
					<label for="name">Nom</label> <input type="text" id="name"
						name="name" value="${name}" required>
				</div>

				<div class="contact-form-input">
					<label for="email">Email</label> <input type="email" id="email"
						name="email" value="${email}" required>
				</div>

				<div class="contact-form-input">
					<label for="phone">Téléphone</label> <input type="tel" id="phone"
						name="phone" value="${phone}">
				</div>

				<div class="contact-form-input">
					<label for="message">Message</label>
					<textarea id="message" name="message" rows="5" required>${message}</textarea>
				</div>

				<div class="text-center mt-4 mb-3">
					<button class="btn btn-b contact-btn">Envoyer</button>
				</div>
			</form>
		</div>


	</div>
</div>
