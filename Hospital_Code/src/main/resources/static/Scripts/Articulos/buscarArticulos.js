const form=document.querySelector("form")
function crearInput(id,lblTxt,value){
    const label=document.createElement("label")
    label.setAttribute("for",id)
    label.textContent=lblTxt
    const input=document.createElement("input")
    input.setAttribute("id",id)
    input.setAttribute("required",true)
    input.value=value

    return [label,input]
}
function crearFilaArticulo(art) {
    let row = document.createElement("tr");
    row.innerHTML = `
        <td>${art.idArticulo}</td>
        <td>${art.nomArticulo}</td>
        <td>${art.cantidad}</td>
        <td>${art.descripcion}</td>
        <td class="td"><button class="editar">Editar</button> <button class="borrar">Borrar</button></td>
        
    `
    row.querySelector(".editar").addEventListener("click",async e=>{
        e.preventDefault()
        const id=art.idArticulo
        document.querySelector("table").innerHTML=""
        try{
            let response=await fetch(`http://localhost:8080/articulos/buscar/${id}`)
            if (response.status===404){
                alert("El id ingresado no esta registrado")
                return
            }
            if (!response.ok){
                throw new Error("Error al buscar el articulo")
            }
            let articulo=await response.json()
            form.innerHTML=""
            let [labelN,nombre]=crearInput("nombre","Nombre: ",articulo.nomArticulo)
            let [labelC,cantidad]=crearInput("cantidad","Cantidad: ",articulo.cantidad)
            let [labelD,descripcion]=crearInput("descripcion","Descripcion: ",articulo.descripcion)

            const actualizarBtn=document.createElement("button")
            actualizarBtn.textContent="Actualizar"
            const volverBtn=document.createElement("a")
            volverBtn.textContent="Volver"
            volverBtn.setAttribute("href","../../html/GestionArticulos/buscaarticulo.html")
            volverBtn.setAttribute("class","button")

            actualizarBtn.addEventListener("click",async e=>{
                e.preventDefault()
                try{
                    let res=await fetch("http://localhost:8080/articulos/actualizar",{
                        method:"POST",
                        headers:{"Content-Type":"application/json"},
                        body:JSON.stringify({
                            idArticulo: id,
                            nomArticulo: nombre.value,
                            cantidad: cantidad.value,
                            descripcion: descripcion.value
                        })
                    })
                    if (!res.ok){
                        throw new Error("Error al actualizar el articulo")
                    }
                    alert("Articulo actualizado correctamente")
                    window.location.reload()
                }catch (e){
                    console.error(e)
                }
            })

            form.appendChild(labelN)
            form.appendChild(nombre)
            form.appendChild(labelC)
            form.appendChild(cantidad)
            form.appendChild(labelD)
            form.appendChild(descripcion)
            form.appendChild(actualizarBtn)
            form.appendChild(volverBtn)
        }catch (e){
            console.error(e)
        }
    })
    row.querySelector(".borrar").addEventListener("click",async e=>{
        e.preventDefault()
        const id=art.idArticulo
        try{
            let res=await fetch(`http://localhost:8080/articulos/buscar/${id}`)
            if (res.status===404){
                alert("El id ingresado no esta registrado")
                return
            }
            if (!res.ok){
                throw new Error("Error al buscar el articulo")
            }
            if (confirm("Seguro que desea eliminar el articulo?")) {
                let response = await fetch(`http://localhost:8080/articulos/borrar/${id}`, {
                    method: "DELETE"
                })
                if (!response.ok) {
                    throw new Error("Error al borrar el articulo")
                }
                alert("Articulo eliminado con exito")
                window.location.reload()
            }
        }catch (e){
            console.error(e)
        }
    })
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