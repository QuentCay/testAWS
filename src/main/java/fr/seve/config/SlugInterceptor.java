package fr.seve.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import fr.seve.entities.AMAP;
import fr.seve.service.AmapService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SlugInterceptor implements HandlerInterceptor {

    private final AmapService amapService;

    public SlugInterceptor(AmapService amapService) {
        this.amapService = amapService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI().substring(1);

        if (path.startsWith("seve/")) {
            path = path.substring(5);
            System.out.println("le path commençait par seve/");
        }

        String[] pathParts = path.split("/");
        System.out.println("le chemin découpé : "+pathParts.toString());
        if (pathParts.length > 0) {
            String slug = pathParts[0];
            System.out.println("le slug extrait : "+slug);
            AMAP amap = amapService.findBySlug(slug);
            if (amap == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "AMAP not found");
                return false;
            }
            
            System.out.println("le slug de l'amap recuperé : "+amap.getSlug());
            request.setAttribute("slug", amap.getSlug());
            request.setAttribute("amap", amap);
            
            Long subscriptionId = amapService.findSubscriptionIdBySlug(slug);
            
            boolean showProducts = subscriptionId == 2 || subscriptionId == 3;
            boolean showActivities = subscriptionId == 3;
            
            request.setAttribute("showProducts", showProducts);
            request.setAttribute("showActivities", showActivities);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && request.getAttribute("amap") != null) {
            modelAndView.addObject("amap", request.getAttribute("amap"));
        }
    }
}
