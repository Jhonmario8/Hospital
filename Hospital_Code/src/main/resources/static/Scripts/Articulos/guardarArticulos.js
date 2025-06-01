const form = document.querySelector("form")
const table = document.querySelector("table")

document.getElementById("nombre").addEventListener("input", async e => {
    e.preventDefault()
    if (e.target.value.length<=3){
        table.style.display="none"
        return
    }
    const name = e.target.value
    table.style.display = "block"
    const tbody = table.querySelector("tbody")
    tbody.innerHTML = ""
    try {
        if (name === "") {
            table.style.display = "none"
            return
        }
        let res = await fetch(`http://localhost:8080/articulos/buscarByName/${name}`)
        if (!res.ok && res.status !== 404) {
            throw new Error("Error al obtener los articulos")
        }

        let articulos = await res.json()
        if (articulos.length === 0) {
            table.style.display = "none"
            return
        }
        articulos.forEach(art => {
            let row = document.createElement("tr")
            row.innerHTML = `
                 <td>${art.nomArticulo}</td>
                 <td>${art.descripcion}</td>
                 <td><div class="activar-btn">Activar</div></td>
                `
            row.addEventListener("click",()=>{
                e.target.value=art.nomArticulo
                table.style.display="none"
                document.getElementById("descripcion").value=art.descripcion
            })
            row.querySelector("div").addEventListener("click",async e=>{
                e.preventDefault()
                try {
                    let res = await fetch(`http://localhost:8080/articulos/activar/${art.idArticulo}`, {
                        method: "PUT"
                    })
                    if (!res.ok) {
                        throw new Error("Error al activar el articulo")
                    }
                    window.location.href="../../html/GestionArticulos/gestionarticulos.html"
                }catch (e){
                    console.error(e)
                }
            })
            tbody.appendChild(row)
        })
    } catch (e) {
        console.error(e)
    }
})
document.getElementById("cantidad").addEventListener("input",e=>{
    if (e.target.value.length>2){
        e.target.value=e.target.value.slice(0,2)
    }
})
document.getElementById("guardarBtn").addEventListener("click", async e => {

    e.preventDefault()
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    const nombre = document.getElementById("nombre").value
    const cantidad = parseInt(document.getElementById("cantidad").value)
    const descripcion = document.getElementById("descripcion").value
    try {
        let res = await fetch("http://localhost:8080/articulos/guardar", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({
                nomArticulo: nombre,
                cantidad: cantidad,
                descripcion: descripcion
            })
        })
        if (!res.ok) {
            throw new Error("Error al guardar el articulo")
        }
        let response = await fetch("http://localhost:8080/articulos/mostrar")
        let articulos = await response.json()
        let idArt = articulos.at(articulos.length - 1).idArticulo
        alert(`Articulo guardado con exito, ID asignado: ${idArt}`)
    } catch (e) {
        console.error(e)
    }
})