<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <div class="container-fluid">
        <h1 class="text-center">Historiques des Commandes</h1>
        
        <table class="table table-bordered table-striped table-hover mb-5">
            <thead>
                <tr>
                    <th class="col-1">Numéro de commande</th>
                    <th class="col-1">Date de Paiement</th>
                    <th class="col-1"> </th>
                    <th class="col-1">Prix Total</th>
                    
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>0000000000${order.id}</td>
                        <td>${order.formattedOrderDate}</td>
                        <td><a href="${pageContext.request.contextPath}/${slug}/order/${order.id}" class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Détails</a></td>
                        <td>${order.totalAmount} €</td>
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>