const errorAnalisis = document.getElementById("analisis-error");
const loaderAnalisis = document.getElementById("analisis-loader");
const botonActualizar = document.getElementById("actualizar-analisis");

const elementosResumen = {
    totalPersonas: document.getElementById("total-personas"),
    totalEmpleados: document.getElementById("total-empleados"),
    empleadosDisponibles: document.getElementById("empleados-disponibles"),
    empleadosNoDisponibles: document.getElementById("empleados-no-disponibles"),
    totalPacientes: document.getElementById("total-pacientes"),
    pacientesHospitalizados: document.getElementById("pacientes-hospitalizados"),
    totalHabitaciones: document.getElementById("total-habitaciones"),
    habitacionesOcupadas: document.getElementById("habitaciones-ocupadas"),
    porcentajePersonalDisponible: document.getElementById("porcentaje-personal-disponible"),
    porcentajeOcupacion: document.getElementById("porcentaje-ocupacion"),
};

const barras = {
    personal: document.getElementById("barra-personal"),
    ocupacion: document.getElementById("barra-ocupacion")
};

const mostrarError = (mensaje) => {
    if (errorAnalisis) {
        errorAnalisis.textContent = mensaje;
        errorAnalisis.style.display = "block";
    }
};

const ocultarError = () => {
    if (errorAnalisis) {
        errorAnalisis.textContent = "";
        errorAnalisis.style.display = "none";
    }
};

const mostrarLoader = () => {
    if (loaderAnalisis) {
        loaderAnalisis.style.display = "flex";
    }
};

const ocultarLoader = () => {
    if (loaderAnalisis) {
        loaderAnalisis.style.display = "none";
    }
};

const limitarPorcentaje = (valor) => Math.min(100, Math.max(0, valor));

const formatearPorcentaje = (valor) => `${valor.toFixed(1)}%`;

const actualizarBarra = (elemento, porcentaje) => {
    if (elemento) {
        elemento.style.width = `${limitarPorcentaje(porcentaje)}%`;
        elemento.setAttribute("aria-valuenow", porcentaje.toFixed(1));
    }
};

const actualizarResumen = (datos) => {
    elementosResumen.totalPersonas.textContent = datos.totalPersonas;
    elementosResumen.totalEmpleados.textContent = datos.totalEmpleados;
    elementosResumen.empleadosDisponibles.textContent = datos.empleadosDisponibles;
    elementosResumen.empleadosNoDisponibles.textContent = datos.empleadosNoDisponibles;
    elementosResumen.totalPacientes.textContent = datos.totalPacientes;
    elementosResumen.pacientesHospitalizados.textContent = datos.pacientesHospitalizados;
    elementosResumen.totalHabitaciones.textContent = datos.totalHabitaciones;
    elementosResumen.habitacionesOcupadas.textContent = datos.habitacionesOcupadas;
    elementosResumen.porcentajePersonalDisponible.textContent = formatearPorcentaje(datos.porcentajePersonalDisponible);
    elementosResumen.porcentajeOcupacion.textContent = formatearPorcentaje(datos.porcentajeOcupacion);

    actualizarBarra(barras.personal, datos.porcentajePersonalDisponible);
    actualizarBarra(barras.ocupacion, datos.porcentajeOcupacion);
};

const cargarResumen = async () => {
    mostrarLoader();
    ocultarError();
    try {
        const respuesta = await fetch("http://localhost:8080/analisis/resumen");
        if (!respuesta.ok) {
            throw new Error("No se pudo obtener el resumen");
        }
        const datos = await respuesta.json();
        actualizarResumen(datos);
    } catch (error) {
        console.error(error);
        mostrarError("No se pudo cargar el análisis de datos. Intenta nuevamente más tarde.");
    } finally {
        ocultarLoader();
    }
};

if (botonActualizar) {
    botonActualizar.addEventListener("click", (e) => {
        e.preventDefault();
        cargarResumen();
    });
}

cargarResumen();
