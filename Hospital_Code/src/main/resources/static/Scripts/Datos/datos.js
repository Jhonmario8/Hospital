const API_BASE = "http://localhost:8080";

const chartConfigurations = [
    {
        endpoint: "/datos/personas-tipo",
        elementId: "personasTipoChart",
        type: "doughnut",
        labelKey: "etiqueta",
        valueKey: "valor",
        datasetLabel: "Personas",
        colors: ["#00b894", "#0984e3", "#6c5ce7", "#fdcb6e"],
        options: {
            plugins: {
                legend: { position: "bottom" }
            }
        }
    },
    {
        endpoint: "/datos/ingresos-estado",
        elementId: "ingresosEstadoChart",
        type: "doughnut",
        labelKey: "etiqueta",
        valueKey: "valor",
        datasetLabel: "Ingresos",
        colors: ["#e17055", "#00cec9", "#fab1a0"],
        options: {
            plugins: {
                legend: { position: "bottom" }
            }
        }
    },
    {
        endpoint: "/datos/citas-mensuales",
        elementId: "citasMensualesChart",
        type: "line",
        labelKey: "periodo",
        valueKey: "total",
        datasetLabel: "Citas",
        colors: ["#00b894"],
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        precision: 0
                    }
                }
            },
            plugins: {
                legend: { display: false }
            }
        }
    }
];

const DEFAULT_COLORS = ["#00b894", "#6c5ce7", "#0984e3", "#fdcb6e", "#e17055", "#00cec9", "#b2bec3"];

function resolveColors(config, dataLength) {
    const palette = config.colors && config.colors.length ? config.colors : DEFAULT_COLORS;
    if (config.type !== "line") {
        return Array.from({ length: dataLength }, (_, index) => palette[index % palette.length]);
    }
    return palette[0];
}

async function fetchData(endpoint) {
    try {
        const response = await fetch(`${API_BASE}${endpoint}`);
        if (!response.ok) {
            throw new Error(`Error ${response.status}`);
        }
        return await response.json();
    } catch (error) {
        console.error(`No fue posible obtener los datos desde ${endpoint}`, error);
        return { error: true };
    }
}

function showMessage(elementId, text) {
    const message = document.querySelector(`.chart-empty[data-chart="${elementId}"]`);
    if (message) {
        if (text) {
            message.textContent = text;
        }
        message.hidden = false;
    }
}

function hideMessage(elementId) {
    const message = document.querySelector(`.chart-empty[data-chart="${elementId}"]`);
    if (message) {
        message.hidden = true;
    }
}

function renderChart(config, data) {
    if (!Array.isArray(data) || data.length === 0) {
        showMessage(config.elementId);
        return;
    }

    const canvas = document.getElementById(config.elementId);
    if (!canvas) {
        return;
    }

    hideMessage(config.elementId);

    const labels = data.map(item => item[config.labelKey]);
    const values = data.map(item => Number(item[config.valueKey] ?? 0));

    const existingChart = Chart.getChart(canvas);
    if (existingChart) {
        existingChart.destroy();
    }

    const datasetConfig = {
        label: config.datasetLabel,
        data: values,
        backgroundColor: resolveColors(config, values.length),
        borderColor: resolveColors(config, values.length),
        borderWidth: config.type === "line" ? 2 : 1,
        tension: config.type === "line" ? 0.3 : undefined,
        fill: config.type === "line"
    };

    new Chart(canvas, {
        type: config.type,
        data: {
            labels,
            datasets: [datasetConfig]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            ...config.options
        }
    });
}

async function inicializarDashboard() {
    const requests = chartConfigurations.map(config => ({ config, promise: fetchData(config.endpoint) }));

    for (const { config, promise } of requests) {
        const data = await promise;
        if (data && data.error) {
            showMessage(config.elementId, "No fue posible cargar la información. Intenta nuevamente más tarde.");
            continue;
        }
        renderChart(config, data);
    }
}

document.addEventListener("DOMContentLoaded", inicializarDashboard);
