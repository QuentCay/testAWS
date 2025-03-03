    document.addEventListener("DOMContentLoaded", function() {
        const openModalBtn = document.getElementById("openSignupModal");
        const modal = document.getElementById("signupModal");
        const closeModalBtn = document.getElementById("closeModal");

        // Ouvrir la modale
        openModalBtn.addEventListener("click", function() {
            modal.style.display = "block";
        });

        // Fermer la modale
        closeModalBtn.addEventListener("click", function() {
            modal.style.display = "none";
        });

        // Fermer la modale si l'utilisateur clique en dehors de celle-ci
        window.addEventListener("click", function(event) {
            if (event.target === modal) {
                modal.style.display = "none";
            }
        });
    });
