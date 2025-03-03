<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <div class="container-fluid">
    <c:if test="${showProducts}">
        <h3 class="text-center">Mes produits</h3>
        <div class="text-end mb-2">
            <a href="${pageContext.request.contextPath}/${slug}/product/addProd" class="btn btn-secondary px-5 py-2 rounded-pill shadow" style=" color: black; background-color: var(--secondary-color)" >Ajouter un produit</a>
        </div>
        <table class="table table-bordered table-striped table-hover mb-5">
            <thead>
                <tr>
                    <th class="d-none">ID</th>
                    <th class="col-1">Nom</th>
                    <th class="col-1">Prix</th>
                    <th class="col-1">Stock</th>
                    <th class="col-1">Catégorie</th>
                    <th class="col-1">Date de disponibilité</th>
                    <th class="col-1">Date limite d'achat</th>    
                    <th class="col-3"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td class="d-none">${product.id}</td>
                        <td>${product.name}</td>
                        <td>${product.price} €</td>
                        <td>${product.stock}</td>
                        <td>${product.category.displayName}</td>
                        <td>${product.formattedAvailableDate}</td>
                        <td>${product.formattedPurchaseDeadlineDate}</td>

                        <td>
                            <div class="btn-container">
                                <a href="${pageContext.request.contextPath}/${slug}/product/${product.id}" class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Détails</a>
                                <a href="${pageContext.request.contextPath}/${slug}/product/editProd/${product.id}" class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Modifier</a>
                                <a href="${pageContext.request.contextPath}/${slug}/product/deleteProd/${product.id}" class="btn btn-secondary" style="background-color: red; color: black;" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce produit ?')">Supprimer</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </c:if>
        <h3 class="text-center">Mes paniers</h3>
        <div class="text-end mb-2">
            <a href="${pageContext.request.contextPath}/${slug}/box/addProd" class="btn btn-secondary px-5 py-2 rounded-pill shadow" style=" color: black; background-color: var(--secondary-color)">Ajouter un panier</a>
        </div>
        <table class="table table-bordered table-striped table-hover mb-5">
            <thead>
                <tr>
                    <th class="d-none">ID</th>
                    <th class="col-1">Nom</th>
                    <th class="col-1">Prix</th>
                    <th class="col-1">Stock</th>
                    <th class="col-1">Fréquence</th>
                    <th class="col-1">Catégorie</th>
                    <th class="col-1">Date de disponibilité</th>
                    <th class="col-1">Date limite d'achat</th>
                   
                    <th class="col-3"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="box" items="${boxes}">
                    <tr>
                        <td class="d-none">${box.id}</td>
                        <td>${box.name}</td>
                        <td>${box.price} €</td>
                        <td>${box.stock}</td>
                        <td>${box.frequency.displayName}</td>
                        <td>${box.category.displayName}</td>
                        <td>${box.formattedAvailableDate}</td>
                        <td>${box.formattedPurchaseDeadlineDate}</td>
                       
                        <td>
                            <div class="btn-container">
                                <a href="${pageContext.request.contextPath}/${slug}/box/${box.id}" class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Détails</a>
                                <a href="${pageContext.request.contextPath}/${slug}/box/editProd/${box.id}" class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Modifier</a>
                                <a href="${pageContext.request.contextPath}/${slug}/box/deleteProd/${box.id}" class="btn btn-secondary" style="background-color: red; color: black;" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce panier ?')">Supprimer</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:if test="${showActivities}">
        <h3 class="text-center">Mes ateliers</h3>
        <div class="text-end mb-2">
            <a href="${pageContext.request.contextPath}/${slug}/activity/addProd" class="btn btn-secondary px-5 py-2 rounded-pill shadow" style=" color: black; background-color: var(--secondary-color)">Ajouter un atelier</a>
        </div>
        <table class="table table-bordered table-striped table-hover mb-5">
            <thead>
                <tr>
                    <th class="d-none">ID</th>
                    <th class="col-1">Nom</th>
                    
                    <th class="col-1">Places disponibles</th>
                    <th class="col-1">Prix</th>
                    <th class="col-1">Date de l'atelier</th>
                    <th class="col-1">Horaire</th>
                    <th class="col-1">Lieu de l'atelier</th>
                    <th class="col-1">Date limite d'achat</th>

                    <th class="col-3"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="activity" items="${activities}">
                    <tr>
                        <td class="d-none">${activity.id}</td>
                        <td>${activity.name}</td>
                        
                        <td>${activity.availableSpace}</td>
                        <td>${activity.price} €</td>
                        <td>${activity.formattedDate}</td>
                        <td>${activity.formattedStartTime} - ${activity.formattedEndTime}</td>
                        <td>${activity.place}</td>
                        <td>${activity.formattedPurchaseDeadlineDate}</td>
                        <td>
                            <div class="btn-container">
                                <a href="${pageContext.request.contextPath}/${slug}/activity/${activity.id}" class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Détails</a>
                                <a href="${pageContext.request.contextPath}/${slug}/activity/editProd/${activity.id}" class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Modifier</a>
                                <a href="${pageContext.request.contextPath}/${slug}/activity/deleteProd/${activity.id}" class="btn btn-secondary" style="background-color: red; color: black;" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet atelier ?')">Supprimer</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </c:if>
    </div>