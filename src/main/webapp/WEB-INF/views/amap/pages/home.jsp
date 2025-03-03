<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="body-container">
	<h1>Bienvenue chez ${amap.name}</h1>
	<div class="home-img">
		<img src="${pageContext.request.contextPath}/configuration/presentationImage/${amap.id}.jpg" alt="" />
	</div>
	<pre class="text-home">${amap.amapSpace.configuration.presentationText}</pre>

</div>
