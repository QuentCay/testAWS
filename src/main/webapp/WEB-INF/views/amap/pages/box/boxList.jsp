<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid mt-5">
	<h1 class="text-center mb-4">Découvrez nos paniers maraîchers</h1>

	<div class="row">
		<c:forEach var="box" items="${boxes}">
			<div class="col-12 col-sm-6 col-md-6 col-lg-3 mt-4 mb-5">
				<div class="card">
					<img
						src="${pageContext.request.contextPath}/${slug}/box/image/${box.id}"
						class="card-img-top" alt="Image du panier"
						style="width: 200; height: 200px; object-fit: cover; display: block; margin: 0 auto;">
					<p class="card-title text-center">${box.name}</p>

					<div class="card-body">
						<p class="card-text mb-4">${box.description}</p>
						<div class="row">
							<div class="col-12 col-md-6">
								<p class="card-text delivery-text">Livraison
									${box.frequency.displayName}</p>
							</div>
							<div
								class="col-12 col-md-6 d-flex justify-content-end align-items-center">
								<span
									class="badge p-2 text-white
						            <c:choose>
						                <c:when test="${box.category.displayName == 'Fruits'}">bg-success</c:when>
						                <c:when test="${box.category.displayName == 'Légumes'}">bg-info</c:when>
						                <c:when test="${box.category.displayName == 'Mixtes'}">bg-warning</c:when>
						                <c:otherwise>bg-secondary</c:otherwise>
						            </c:choose>
						        ">
									${box.category.displayName} </span>
							</div>
						</div>
						<div
							class="row d-flex justify-content-center align-items-center mt-4">
							<p class="card-text text-center font-weight-bold">${box.price}
								€ / mois</p>
						</div>
						<form action="${pageContext.request.contextPath}/${slug}/cart/add"
							method="post" class="text-center mt-3">
							<input type="hidden" name="boxId" value="${box.id}" />
							<input type="hidden" name="genre" value="BOX" />
							<button type="submit" class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Commander</button>
						</form>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
