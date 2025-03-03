<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<div class="body-container">
<sec:authorize access="hasRole('AMAP_ADMIN') or hasRole('AMAP_SUPERVISOR')">
	<h1>Bienvenue dans l'espace admin de ${amap.name}</h1>
	
    </sec:authorize>
</div>
