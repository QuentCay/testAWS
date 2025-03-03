<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid mt-5">
	<h1 class="text-center mb-4">Découvrez nos produits</h1>

	<div class="row">
		<c:forEach var="product" items="${products}">
			<div class="col-12 col-sm-6 col-md-6 col-lg-3 mt-4 mb-5">
				<div class="card">
					<img
						src="${pageContext.request.contextPath}/${slug}/product/image/${product.id}"
						class="card-img-top" alt="Image du produit" style="width: 200; height: 200px; object-fit: cover; display: block; margin: 0 auto;">
					<p class="card-title text-center">${product.name}</p>

					<div class="card-body">
						<p class="card-text mb-4" >${product.description}</p>
						<div class="row">
						<div class="col-12 col-md-6">
                         
                            </div>
							<div class="col-12 col-md-6 d-flex justify-content-end align-items-center">
								<span
									class="badge p-2 text-white
						            <c:choose>
						                <c:when test="${product.category.displayName == 'Fruits'}">bg-success</c:when>
						                <c:when test="${product.category.displayName == 'Légumes'}">bg-info</c:when>
						                <c:when test="${product.category.displayName == 'Boulangerie'}">bg-primary</c:when>
						                <c:when test="${product.category.displayName == 'Boissons'}">bg-teal</c:when>
						                <c:when test="${product.category.displayName == 'Epicerie sucrée'}">bg-purple</c:when>
						                <c:when test="${product.category.displayName == 'Epicerie salée'}">bg-orange</c:when>
						                <c:when test="${product.category.displayName == 'Boucherie'}">bg-pink</c:when>
						                  <c:when test="${product.category.displayName == 'Produits laitiers et oeufs'}">bg-indigo</c:when>
						                <c:otherwise>bg-secondary</c:otherwise>
						            </c:choose>
						        ">
									${product.category.displayName} </span>
							</div>
						</div>
						<div class="row d-flex justify-content-center align-items-center mt-4">
							<p class="card-text text-center font-weight-bold">${product.price} €</p>
						</div>
						<div class="row d-flex justify-content-center mt-3">
							<div class="col-3">
								<input type="number" name="quantity" min="1" value="1" class="form-control">
							</div>
						</div>
						<form action="${pageContext.request.contextPath}/${slug}/cart/add/product"
							method="post" class="text-center mt-3">
							<input type="hidden" name="productId" value="${product.id}" />
							<input type="hidden" name="genre" value="PRODUCT" />
							<button type="submit" class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Commander</button>
						</form>
				</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>



