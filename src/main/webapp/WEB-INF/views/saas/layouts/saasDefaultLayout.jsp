<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title><tiles:insertAttribute name="title" /></title>

<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/saas/main.css" />
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/saas/favicon.ico">
	

<!-- CSS dynamiques -->
<c:if test="${not empty css}">
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}${css}" />
</c:if>

</head>

<body>
	<header class="stickyHeader">
		<tiles:insertAttribute name="header" />
	</header>

	<main>
		<tiles:insertAttribute name="main" />
	</main>

	<footer>
		<tiles:insertAttribute name="footer" />
	</footer>

	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>

</body>
</html>