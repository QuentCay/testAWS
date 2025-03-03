package fr.seve.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/contact")
public class ContactController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Gérer la méthode GET pour afficher le formulaire
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Rediriger vers la page de formulaire de contact (contact.jsp)
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/saas/contact.jsp");;
        dispatcher.forward(request, response);
    }

    // Gérer la méthode POST pour traiter le formulaire
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données du formulaire
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        // Logique métier (valider, enregistrer, envoyer un email, etc.)

        // Passer les données à la vue et afficher un message de confirmation sur la même page
        request.setAttribute("confirmationMessage", "Votre message a bien été envoyé !");
        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("subject", subject);
        request.setAttribute("message", message);

        // Retourner à la page de formulaire avec les données et un message de confirmation
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/saas/account/contact.jsp");
        dispatcher.forward(request, response);
    }
}