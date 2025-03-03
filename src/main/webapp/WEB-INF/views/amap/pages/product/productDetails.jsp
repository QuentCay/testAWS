<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1 class="text-center">Détails du produit</h1>

<div class="card my-2 p-4 shadow-sm">
    <div class="card-body">
        <div class="row">
            <div class="col-md-6">
                <p><strong>Nom :</strong> ${product.name}</p>
                <p><strong>Description :</strong> ${product.description}</p>
                <p><strong>Catégorie :</strong> ${product.category.displayName}</p>
                <p><strong>Prix :</strong> ${product.price} €</p>
                <p><strong>Stock :</strong> ${product.stock}</p>
            </div>
            <div class="col-md-6">
                <p><strong>Date de disponibilité :</strong> ${product.formattedAvailableDate}</p>
                <p><strong>Date limite d'achat :</strong> ${product.formattedPurchaseDeadlineDate}</p>
                <p><strong>Date de création :</strong> ${product.formattedCreationDate}</p>
                <p><strong>Date de dernière modification :</strong> ${product.formattedLastModifiedDate}</p>
                <sec:authorize access="hasRole('AMAP_ADMIN') or hasRole('AMAP_SUPERVISOR')">
<a href="${pageContext.request.contextPath}/${slug}/product/admin" class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Retour à la liste</a>
</sec:authorize>
<c:if test="${amapUser.type == 'PRODUCER'}">
<a href="${pageContext.request.contextPath}/${slug}/myproducts" class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Retour à la liste</a>
</c:if>
            </div>
        </div>
        
    </div>
    
</div>

