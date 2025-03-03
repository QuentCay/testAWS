// Bloque le calendrier avant la date du jour pour la date de disponibilité
document.getElementById('availableDate').setAttribute('min', new Date().toISOString().split('T')[0]);

// Bloque le calendrier pour la date limite d'achat (date de dispo + 7j)
function updatePurchaseDeadlineDate() {
    const availableDate = document.getElementById('availableDate').value;
    const purchaseDeadlineDateField = document.getElementById('purchaseDeadlineDate');

    if (purchaseDeadlineDateField.value && availableDate) {
        purchaseDeadlineDateField.value = '';
    }

    if (availableDate) {
        const availableDateObj = new Date(availableDate);
        availableDateObj.setDate(availableDateObj.getDate() + 7);
        const purchaseDeadlineDate = availableDateObj.toISOString().split('T')[0];
        purchaseDeadlineDateField.setAttribute('min', purchaseDeadlineDate);
    }
}

// Mise à jour de la date limite d'achat quand la date de dispo change
document.getElementById('availableDate').addEventListener('change', updatePurchaseDeadlineDate);