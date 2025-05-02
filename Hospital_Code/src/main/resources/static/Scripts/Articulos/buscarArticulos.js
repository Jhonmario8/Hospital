
function crearFilaArticulo(art) {
    let row = document.createElement("tr");
    row.innerHTML = `
        <td>${art.idArticulo}</td>
        <td>${art.nomArticulo}</td>
        <td>${art.cantidad}</td>
        <td>${art.descripcion}</td>
    `;

    if (art.habitaciones != null) {
        let cad = art.habitaciones.map(h => "ID: " + h.numHabitacion).join(" , ");
        row.innerHTML += `<td>${cad}</td>`;
    }

    return row;
}

document.addEventListener("DOMContentLoaded",async e=>{
    try {
        let response = await fetch("http://localhost:8080/articulos/mostrar")
        if (!response.ok) {
            throw new Error("Error al obtenero los articulos")
        }
        let json = await response.json()
        let tabla = document.getElementById("articulos-tabla")
        json.forEach(art => {
            let row=crearFilaArticulo(art);
            tabla.appendChild(row)
        })
    }catch (e){
        console.error(e)
    }
})
document.getElementById("id").addEventListener("input", async e => {
    e.preventDefault();
    const id = e.target.value.trim();


    let info = document.getElementById("info-articulo");
    let tabla = document.getElementById("articulos-tabla");
    tabla.innerHTML = "";

    if (id === "") {

        try {
            let res = await fetch("http://localhost:8080/articulos/mostrar");
            if (!res.ok) throw new Error("Error al obtener los articulos");

            let json = await res.json();
            json.forEach(art => {
                let row = crearFilaArticulo(art);
                tabla.appendChild(row);
            });
            info.textContent = "";
        } catch (e) {
            console.error(e);
        }
        return
    }

    try {
        let res = await fetch(`http://localhost:8080/articulos/buscar/${id}`);
        if (res.status === 404) {
            info.textContent = "El ID ingresado no está registrado";
            info.style.color = "red";
            return
        }

        if (!res.ok) {
            throw new Error("Error al buscar el articulo");
        }

        let art = await res.json();
        let row = crearFilaArticulo(art);
        tabla.appendChild(row);
        info.textContent = "";

    } catch (e) {
        console.error(e);
    }
});