<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <div class="container-fluid">
        <h1 class="text-center">Paniers de l'AMAP</h1>
        <div class="text-end mb-2">
            <a href="${pageContext.request.contextPath}/${slug}/box/add" class="btn btn-secondary px-5 py-2 rounded-pill shadow" style=" color: black; background-color: var(--secondary-color)">Ajouter un panier</a>
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
                    <th class="col-1">Date de création</th>
                    <th class="col-1">Producteur</th>
                    <th class="col-2"></th>
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
                        <td>${box.formattedCreationDate}</td>
                        <td>${box.amapProducerUser.amapUser.name} ${box.amapProducerUser.amapUser.firstname}</td>
                        <td>
                            <div class="btn-container">
                                <a href="${pageContext.request.contextPath}/${slug}/box/${box.id}" class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Détails</a>
                                <a href="${pageContext.request.contextPath}/${slug}/box/edit/${box.id}" class="btn btn-secondary"style=" color: black; background-color: var(--secondary-color)">Modifier</a>
                                <a href="${pageContext.request.contextPath}/${slug}/box/delete/${box.id}" class="btn btn-secondary" style="background-color: red; color: black;" onclick="return confirm('Êtes-vous sûr de vouloir supprimer ce panier ?')">Supprimer</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>