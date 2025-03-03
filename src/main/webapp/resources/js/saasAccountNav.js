function toggleSubMenu(event) {
	event.preventDefault();
	event.stopPropagation();
	const subMenu = document.querySelector('.submenus');

	if (subMenu.style.display === "none" || subMenu.style.display === "") {
		subMenu.style.display = "block";
	} else {
		subMenu.style.display = "none";
	}
}


document.addEventListener('DOMContentLoaded', function() {
	const subMenu = document.querySelector('.submenus');
	if (subMenu) {
		subMenu.style.display = "block";
	}
});

