document.getElementById('submitButton').addEventListener('click', function () {
    const form = document.getElementById('contactForm');
    const successMessage = document.getElementById('successMessage');
    let isValid = true;

    // Vérifie que tous les champs requis sont remplis
    form.querySelectorAll('input, textarea').forEach(field => {
        if (!field.checkValidity()) {
            field.classList.add('is-invalid');
            isValid = false;
        } else {
            field.classList.remove('is-invalid');
        }
    });

    if (isValid) {
        // Affiche le message de succès
        successMessage.classList.remove('d-none');

        // Réinitialise les champs
        form.reset();

        // Cache le message après quelques secondes
        setTimeout(() => {
            successMessage.classList.add('d-none');
        }, 5000);
    }
});
