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
        <td class="td" style="margin-top: 15px; padding-bottom: 35px;"><button class="editar">Editar</button> <button class="borrar">Inactivar</button> <button class="asignar">Asignar</button></td>
        
    `
    row.querySelector(".editar").addEventListener("click",async e=>{
        e.preventDefault()
        const id=art.idArticulo
        document.getElementById("tabla").innerHTML=""
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
            volverBtn.setAttribute("href","../../html/GestionArticulos/gestionarticulos.html")
            volverBtn.setAttribute("class","button")

            cantidad.addEventListener("input",ev => {
                if (cantidad.value.length>2){
                    cantidad.value=cantidad.value.slice(0,2)
                }
            })


            actualizarBtn.addEventListener("click",async e=>{
                e.preventDefault()
                if (!form.checkValidity()){
                    form.reportValidity()
                    return
                }
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
            cantidad.type="number"
            cantidad.min="1"
            cantidad.max="50"
            cantidad.step="1"
            nombre.maxLength="20"
            descripcion.maxLength="30"
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
            if (confirm("Seguro que desea inactivar el articulo?")) {
                let response = await fetch(`http://localhost:8080/articulos/borrar/${id}`, {
                    method: "DELETE"
                })
                if (!response.ok) {
                    throw new Error("Error al borrar el articulo")
                }
                alert("Articulo inactivado con exito")
                window.location.reload()
            }
        }catch (e){
            console.error(e)
        }
    })
    row.querySelector(".asignar").addEventListener("click",async e=>{
        e.preventDefault()
        document.getElementById("tabla").innerHTML=""
        form.innerHTML=""
        const idArt=art.idArticulo
        let [labelH,habitacion]=crearInput("habitacion","Nro. Habitacion: ","")
        habitacion.placeholder="Ingrese numero habitacion"
        let [labelC,cantidad]=crearInput("cantidad","Cantidad: ","")
        cantidad.placeholder="Ingrese la cantidad"
        let info=document.createElement("p")
        let div=document.createElement("div")
        div.style.width="100vw";
        let tbl=document.createElement("table")
        tbl.setAttribute("class","styled-tables")
        tbl.innerHTML=`
                    <thead>
                        <tr>
                            <th>Numero</th>
                            <th>Tipo</th>
                            <th>Capacidad</th>
                        </tr>
                    </thead>
                    <tbody>
                       
                    </tbody>
                `
        tbl.style.display="none"
        let asignarBtn=document.createElement("button")
        asignarBtn.textContent="Asignar"
        let volverBtn=document.createElement("button")
        volverBtn.textContent="Volver"
        volverBtn.addEventListener("click",e=> {
            e.preventDefault()
            window.location.reload()
        })
        habitacion.addEventListener("input",async e=>{
            e.preventDefault()
            const id=e.target.value
            const tbody=tbl.querySelector("tbody")
            tbody.innerHTML=""
            info.textContent=""
            if(id===""){
                tbl.style.display="none"
                return
            }
            try{
                let res=await fetch(`http://localhost:8080/habitaciones/containing/${id}`)
                if (res.status===404){
                    let mensaje=await res.text()
                    info.textContent=mensaje
                    info.style.color="red"
                    tbl.style.display="none"
                    return
                }
                if (!res.ok){
                    throw new Error("Error al obtener las habitaciones")
                }
                tbl.style.display="block"
                let habitaciones=await res.json()
                habitaciones.forEach(hab=>{
                    let row=document.createElement("tr")
                    row.innerHTML=`
                        <td>${hab.numHabitacion}</td>
                        <td>${hab.tipoHabitacion}</td>
                        <td>${hab.capacidad}</td>
                    `
                    row.addEventListener("click",e=>{
                        e.preventDefault()
                        habitacion.value=hab.numHabitacion
                        tbody.innerHTML=row.innerHTML
                    })
                    tbody.appendChild(row)
                })
                asignarBtn.addEventListener("click",async e=>{
                    e.preventDefault()
                    try{
                        let res=await fetch(`http://localhost:8080/articulos/asignar/${idArt}/habitacion/${habitacion.value}/cantidad/${cantidad.value}`,{
                            method:"POST"
                        })
                        let json=await res.json()
                        if (json) {
                            if (!res.ok) {
                                throw new Error("Error al asignar el articulo")
                            }
                            alert("articulo asignado con exito")
                            window.location.reload()
                        }else {
                            alert("No hay suficietes articulos")
                        }

                    }catch (e){
                        console.error(e)
                    }
                })
            }catch (e){
                console.error(e)
            }
        })
        form.appendChild(labelH)
        form.appendChild(habitacion)
        form.appendChild(info)
        form.appendChild(tbl)
        form.appendChild(labelC)
        form.appendChild(cantidad)
        form.appendChild(asignarBtn)
        form.appendChild(volverBtn)
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
        console.log(json)
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