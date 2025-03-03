<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-fluid mt-5">
    <h1 class="text-center mb-4">Découvrez nos ateliers</h1>

    <div class="row">
        <c:forEach var="activity" items="${activities}">
            <div class="col-12 col-sm-6 col-md-6 col-lg-3 mt-4 mb-5">
                <div class="card">
                 <img
						src="${pageContext.request.contextPath}/${slug}/activity/image/${activity.id}"
						class="card-img-top" alt="Image de l'atelier"
						style="width: 200; height: 200px; object-fit: cover; display: block; margin: 0 auto;">
						
                    <p class="card-title text-center">${activity.name}</p>
               
                    <div class="card-body">
                        <p class="card-text mb-4">${activity.description}</p>
                        <div class="row">
                            <div class="col-12 col-md-6">
                           
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calendar3" viewBox="0 0 16 16">
  <path d="M14 0H2a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2M1 3.857C1 3.384 1.448 3 2 3h12c.552 0 1 .384 1 .857v10.286c0 .473-.448.857-1 .857H2c-.552 0-1-.384-1-.857z"/>
  <path d="M6.5 7a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2m3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2"/>
</svg>
                         
                                <p class="card-text text"> ${activity.formattedDate} </p>
                            </div>
                            <div class="col-12 col-md-6 d-flex justify-content-end align-items-center">
                            
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-clock" viewBox="0 0 16 16">
  <path d="M8 3.5a.5.5 0 0 0-1 0V9a.5.5 0 0 0 .252.434l3.5 2a.5.5 0 0 0 .496-.868L8 8.71z"/>
  <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16m7-8A7 7 0 1 1 1 8a7 7 0 0 1 14 0"/>
</svg>
                          
								<span class="card-text text">
						            ${activity.formattedStartTime} - ${activity.formattedEndTime}
						        </span>                            
							</div>
							 <div class="col-12 ">
							 <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-map" viewBox="0 0 16 16">
  <path fill-rule="evenodd" d="M15.817.113A.5.5 0 0 1 16 .5v14a.5.5 0 0 1-.402.49l-5 1a.5.5 0 0 1-.196 0L5.5 15.01l-4.902.98A.5.5 0 0 1 0 15.5v-14a.5.5 0 0 1 .402-.49l5-1a.5.5 0 0 1 .196 0L10.5.99l4.902-.98a.5.5 0 0 1 .415.103M10 1.91l-4-.8v12.98l4 .8zm1 12.98 4-.8V1.11l-4 .8zm-6-.8V1.11l-4 .8v12.98z"/>
</svg>
                             <p class="card-text text"> ${activity.place} </p>
                            </div>
                        </div>
                        <div class="row d-flex justify-content-center align-items-center mt-4">
    						<p class="card-text text-center font-weight-bold">${activity.price} € </p>
						</div>
						<form action="${pageContext.request.contextPath}/${slug}/cart/add/activity"
							method="post" class="text-center mt-3">
							<input type="hidden" name="activityId" value="${activity.id}" />
								<input type="hidden" name="genre" value="ACTIVITY" />
							<button type="submit" class="btn btn-secondary" style=" color: black; background-color: var(--secondary-color)">Commander</button>
						</form>
						
						

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
