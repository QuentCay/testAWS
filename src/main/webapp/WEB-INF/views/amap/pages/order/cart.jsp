<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container mt-5">
	<h1 class="text-center">Votre panier</h1>
	<c:if test="${not empty cart.items}">
		<table class="table">
			<thead>
				<tr>
					<th>Nom</th>
					<th>Prix unitaire</th>
					<th>Quantité</th>
					<th>Total</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${cart.items}">
					<tr>

						<td>${item.box.name != null ? item.box.name : (item.product.name != null ? item.product.name : (item.activity.name != null ? item.activity.name : item.name))}</td>
						<td>${item.box.price != null ? item.box.price : (item.product.price != null ? item.product.price : (item.activity.price != null ? item.activity.price : item.price))}
							€</td>
						<td><c:choose>
								<c:when test="${!empty item.genre && item.genre == 'Cotisation annuelle'}">
									<form
										action="${pageContext.request.contextPath}/${slug}/cart/update"
										method="post">
										<input type="hidden" name="itemId" value="${item.id}" />
										<input type="hidden" name="genre" value="BOX" />
								</c:when>
								<c:when test="${!empty item.genre && item.genre == 'BOX'}">
									<form
										action="${pageContext.request.contextPath}/${slug}/cart/update"
										method="post">
										<input type="hidden" name="itemId" value="${item.box.id}" />
										<input type="hidden" name="genre" value="BOX" />
								</c:when>
								<c:when test="${!empty item.genre && item.genre == 'PRODUCT'}">
									<form
										action="${pageContext.request.contextPath}/${slug}/cart/update/product"
										method="post">
										<input type="hidden" name="productId"
											value="${item.product.id}" /> <input type="hidden"
											name="genre" value="PRODUCT" />
								</c:when>
								<c:otherwise>
									<form
										action="${pageContext.request.contextPath}/${slug}/cart/update/activity"
										method="post">
										<input type="hidden" name="activityId"
											value="${item.activity.id}" /> <input type="hidden"
											name="genre" value="ACTIVITY" />
								</c:otherwise>
							</c:choose> <input type="number" name="quantity" value="${item.quantity}"
							min="0" class="form-control d-inline" style="width: 70px;" />
							<button type="submit" class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Modifier</button>
							</form>
							</td>
						<td>${item.totalPrice}€</td>
						<td>
							<form
								action="${pageContext.request.contextPath}/${slug}/cart/remove"
								method="post">
								<input type="hidden" name="itemId" value="${item.id}" /> <input
									type="hidden" name="genre" value="BOX" />
								<button type="submit" class="btn btn-secondary" style=" color: white; background-color: red;">Supprimer</button>
							</form>
						</td>
						<td class="">${genre}</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="d-flex justify-content-between align-items-center mb-4">
			<h3>Total : ${cart.totalPrice} €</h3>
			<form action="${pageContext.request.contextPath}/${slug}/order/add"
				method="post">
				<button type="submit" class="btn btn-secondary" style=" color: white; background-color: green;">Valider la commande</button>
			</form>
			<form action="${pageContext.request.contextPath}/${slug}/cart/clear"
				method="post">
				<button type="submit" class="btn btn-secondary" style=" color: white; background-color: red;">Vider
					le panier</button>
			</form>
			
		</div>
	</c:if>
	<c:if test="${empty cart.items}">
		<p class="text-center">Votre panier est vide.</p>
	</c:if>
</div>
