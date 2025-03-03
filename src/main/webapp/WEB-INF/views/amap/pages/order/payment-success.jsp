<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



	<h1>Merci ! Votre commande a bien été validée.</h1>
	
	<p> Vous pourrez récupérer vos produits et paniers lors de votre prochain passage au point de distribution. </p>
	<p> Pour les ateliers, l'organisateur vous contactera dans les 48h afin de valider votre inscription. </p>
	<p> Vous pouvez consulter vos commandes à tout moment <a  style="text-decoration: underline; cursor: pointer;" onclick="window.location.href='${pageContext.request.contextPath}/${slug}/order/list'">ici</a>.<p> 



