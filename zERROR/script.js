document.addEventListener('DOMContentLoaded', function() {
    // Seleccionamos todos los enlaces de la página
    const enlaces = document.querySelectorAll('a');

    enlaces.forEach(enlace => {
        // Añadimos un evento a cada enlace para gestionar errores
        enlace.addEventListener('click', function(event) {
            event.preventDefault(); // Prevenimos que el enlace se abra de forma normal

            // Intentamos acceder al enlace
            fetch(enlace.href)
                .then(response => {
                    if (!response.ok) {
                        // Si el enlace no está disponible (por ejemplo, 404)
                        window.location.href = 'zERROR/OUPSSSS/Oupss.html';  // Redirige a la página de error
                    } else {
                        // Si el enlace está bien, lo abrimos
                        window.location.href = enlace.href;
                    }
                })
                .catch(() => {
                    // Si hay algún problema de red (por ejemplo, el servidor no responde)
                    window.location.href = 'zERROR/OUPSSSS/Oupss.html';  // Redirige a la página de error
                });
        });
    });
});