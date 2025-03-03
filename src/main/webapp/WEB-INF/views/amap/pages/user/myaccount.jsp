<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<div class="container mt-5 d-flex justify-content-center">
    <div class="card shadow p-3 mb-5 bg-body rounded-3" style="min-width: 100%; background-color: var(--tertiary-color); box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);">
        <h2 class="text-center fw-bold mb-4">Vos informations personnelles</h2>

        <div class="mb-4 d-flex ">
            <label class="fw-800"><strong>Nom :&nbsp;</strong></label>
            <span> ${amapUser.name}</span>
        </div>

        <div class="mb-4 d-flex ">
            <label class="fw-bold"><strong>Prénom :&nbsp;</strong></label>
            <span> ${amapUser.firstname}</span>
        </div>

        <div class="mb-4 d-flex ">
            <label class="fw-bold"><strong>Email :&nbsp;</strong></label>
            <span> ${amapUser.email}</span>
        </div>

        <div class="mb-4 d-flex ">
            <label class="fw-bold"><strong>Téléphone :&nbsp;</strong></label>
            <span> ${amapUser.phone}</span>
        </div>

<sec:authorize access="hasRole('AMAP_USER')">
        <c:choose>
        
            <c:when test="${amapUser.type == 'INDIVIDUAL'}">
                
                    
                    <p class="text-center fw-bold mb-4">
                        <c:choose>
                            <c:when test="${amapUser.individualUser.volunteer == true}"><strong>Vous êtes bénévole.</strong></c:when>
                            <c:otherwise><strong>Vous n'êtes pas bénévole.</strong></c:otherwise>
                        </c:choose>
                    </p>
             
            </c:when>
          </c:choose> 
          </sec:authorize>
            
		<c:choose>
            <c:when test="${amapUser.type == 'PRODUCER'}">
                <div class="mb-4 d-flex ">
                    <label class="fw-bold"><strong>RIB :&nbsp;</strong></label>
                    <span>${amapUser.producerUser.rib}</span>
                </div>
                <div class="mb-4 d-flex ">
                    <label class="fw-bold"><strong>Adresse :&nbsp;</strong></label>
                    <span>${amapUser.producerUser.address}</span>
                </div>
                 <p class="text-center fw-bold mb-4"><strong>Vous êtes Producteur chez ${slug}.</strong>
            </c:when>

            <c:when test="${amapUser.type == 'WORKS_COMITTEE'}">
                <div class="mb-4 d-flex ">
                    <label class="fw-bold"><strong>Nom de l'entreprise :&nbsp;</strong></label>
                    <span>${amapUser.worksComitteeUser.companyName}</span>
                </div>
                <div class="mb-4 d-flex ">
                    <label class="fw-bold"><strong>Numéro SIRET :&nbsp;</strong></label>
                    <span>${amapUser.worksComitteeUser.siret}</span>
                </div>
            </c:when>
            </c:choose>
        <sec:authorize access="hasRole('AMAP_ADMIN')">
            <p class="text-center fw-bold mb-4"><strong>Vous êtes Administrateur chez ${slug}.</strong></p>
            </sec:authorize>
<div class="mt-5 text-center">
            <a href=<c:choose>
            <c:when test="${amapUser.type == 'INDIVIDUAL'}">
            "${pageContext.request.contextPath}/${slug}/individual/update"
            </c:when>
            <c:when test="${amapUser.type == 'PRODUCER'}">
            "${pageContext.request.contextPath}/${slug}/producer/update"
            </c:when>
            <c:when test="${amapUser.type == 'WORKS_COMITTEE'}">
            "${pageContext.request.contextPath}/${slug}/works-comittee/update"
            </c:when>
            </c:choose>
             class="btn btn-secondary px-5 py-2 rounded-pill shadow" style="background-color: var(--secondary-color)" >Modifier</a>
        </div>
      
    </div>
    
</div>


  
