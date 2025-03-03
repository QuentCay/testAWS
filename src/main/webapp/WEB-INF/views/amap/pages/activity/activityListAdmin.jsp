<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <div class="container-fluid">
        <h1 class="text-center">Ateliers de l'AMAP</h1>
        <div class="text-end mb-2">
            <a href="${pageContext.request.contextPath}/${slug}/activity/add" class="btn btn-secondary px-5 py-2 rounded-pill shadow" style=" color: black; background-color: var(--secondary-color)" >Ajouter un atelier</a>
        </div>
        <table class="table table-bordered table-striped table-hover mb-5">
            <thead>
                <tr>
                    <th class="d-none">ID</th>
                    <th class="col-1">Nom</th>
                    <th class="col-1">Prix</th>
                    <th class="col-1">Nombre de places disponibles</th>
                    <th class="col-1">Date de l'atelier</th>
                    <th class="col-1">Horaire</th>
                    <th class="col-1">Lieu de l'atelier</th>
                    <th class="col-1">Date limite d'achat</th>
                    <th class="col-1">Date de création</th>
                    <th class="col-1">Producteur</th>
                    <th class="col-2"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="activity" items="${activities}">
                    <tr>
                        <td class="d-none">${activity.id}</td>
                        <td>${activity.name}</td>
                        <td>${activity.price} €</td>
                        <td>${activity.availableSpace}</td>
                        <td>${activity.formattedDate}</td>
                        <td>${activity.formattedStartTime} - ${activity.formattedEndTime}</td>
                        <td>${activity.place}</td>
                        <td>${activity.formattedPurchaseDeadlineDate}</td>
                        <td>${activity.formattedCreationDate}</td>
                        <td>${activity.amapProducerUser.amapUser.name} ${activity.amapProducerUser.amapUser.firstname}</td>
                        <td>
                            <div class="btn-container">
                                <a href="${pageContext.request.contextPath}/${slug}/activity/${activity.id}" class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Détails</a>
                                <a href="${pageContext.request.contextPath}/${slug}/activity/edit/${activity.id}" class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Modifier</a>
                                <a href="${pageContext.request.contextPath}/${slug}/activity/delete/${activity.id}" class="btn btn-secondary" style="background-color: red; color: black;"onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet atelier ?')">Supprimer</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>