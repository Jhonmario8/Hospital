(function () {
    document.addEventListener("DOMContentLoaded", () => {
        const usuario = localStorage.getItem("usuarioActual");
        const greetingTargets = document.querySelectorAll('[data-user-greeting]');

        if (usuario) {
            greetingTargets.forEach(element => {
                element.textContent = `Hola, ${usuario}`;
                element.hidden = false;
            });

            const navList = document.querySelector(".nav-list");
            if (navList && !navList.querySelector('.nav-greeting')) {
                const greetingItem = document.createElement('li');
                greetingItem.className = 'nav-greeting';
                greetingItem.textContent = `Hola, ${usuario}`;
                const firstItem = navList.firstElementChild;
                if (firstItem) {
                    navList.insertBefore(greetingItem, firstItem);
                } else {
                    navList.appendChild(greetingItem);
                }
            }
        } else {
            greetingTargets.forEach(element => {
                element.remove();
            });
        }

    });
})();
