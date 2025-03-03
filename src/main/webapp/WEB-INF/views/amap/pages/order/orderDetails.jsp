<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1 class="text-center">Détails de la commande</h1>

<div class="card my-2 p-4 shadow-sm">
    <div class="card-body">
        <div class="row">
            <div class="col-md-6">
                <p><strong>Numéro de commande :</strong>0000000000${order.id}</p>
                    <div class="col-md-6">
                <div class="order-summary">
                    
                    <table class="table">
                        <thead>
                            <tr>
                            	<th>Genre</th>
                                <th>Nom</th>
                                <th>Quantité</th>
                                <th>Prix Unitaire</th>
                                <th>Total</th>	
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${orderItems}">
                                <tr>
                                <td>${item.genre}</td>
                                    <td>${item.name}</td>
                                    <td>${item.quantity}</td>
                                    <td>${item.price} €</td>
                                    <td>${item.price * item.quantity} €</td>
                                </tr>
                            </c:forEach>
                            <tr class="total-row">
                                <td colspan="3">Total de la Commande</td>
                                <td>${order.totalAmount} €</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <a href="${pageContext.request.contextPath}/${slug}/order/list" class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Retour à la liste</a>
            </div>
            </div>
            
        </div>
    </div>
</div>


