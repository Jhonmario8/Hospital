let tabla=document.getElementById("servicios-tabla")
const form=document.querySelector("form")
function crearInput(id,lblTxt,value){
    const label=document.createElement("label")
    label.textContent=lblTxt
    label.setAttribute("for",id)
    const input=document.createElement("input")
    input.value=value
    input.setAttribute("id",id)
    input.setAttribute("required",true)

    return [label,input]
}
function crearFila(ser){
    let row=document.createElement("tr")
    row.innerHTML=`
            <td>${ser.codServicio}</td>
            <td>${ser.nomServicio}</td>
            <td>${ser.precioServicio}</td>
            <td style="padding: 10px 20px;">${ser.detallesServicio}</td>
            <td class="td" style="margin-top: 30px; padding-bottom: 50px;"><button class="editar">Editar</button><button class="borrar">Borrar</button></td>
            `
    row.querySelector(".editar").addEventListener("click",async e=>{
        e.preventDefault()

        const id=ser.codServicio
        document.querySelector("table").innerHTML=""
        try{
            let response=await fetch(`http://localhost:8080/servicios/buscar/${id}`)
            if (response.status===404){
                alert("El id ingresado no esta registrado")
                form.reset()
                return
            }
            if (!response.ok){
                throw new Error("Error al buscar el servicio")
            }
            let servicio=await response.json()

            form.innerHTML=""

            let [labelN,nombre]=crearInput("nombre","Nombre: ",servicio.nomServicio)
            let [labelP,precio]=crearInput("precio","Precio: ",servicio.precioServicio)
            let [labelD,detalles]=crearInput("detalles","Detalles: ",servicio.detallesServicio)

            const actualizarBtn=document.createElement("button")
            actualizarBtn.textContent="Actualizar"

            const volverBtn=document.createElement("a")
            volverBtn.textContent="Volver"
            volverBtn.setAttribute("href","../../html/GestionServicios/gestionservicios.html")
            volverBtn.setAttribute("class","button")

            precio.addEventListener("input",ev => {
                if (precio.value.length>5){
                    precio.value=precio.value.slice(0,5)
                }
            })

            actualizarBtn.addEventListener("click",async e=>{
                e.preventDefault()
                if (!form.checkValidity()){
                    form.reportValidity()
                    return
                }
                try {
                    let res = await fetch(`http://localhost:8080/servicios/actualizar`, {
                        method: "POST",
                        headers: {"Content-Type":"application/json"},
                        body: JSON.stringify({
                            codServicio: id,
                            nomServicio: nombre.value,
                            precioServicio: precio.value,
                            detallesServicio: detalles.value
                        })
                    })
                    if (!res.ok) {
                        throw new Error("Error al actualizar el servicio")
                    }
                    alert("Servicio actualizado correctamente")
                    window.location.reload()
                }catch (e){
                    console.error(e)
                }
            })
            precio.type="number"
            precio.min="100"
            precio.max="10000"
            nombre.minLength="3"
            nombre.maxLength="20"
            detalles.maxLength="30"
            form.appendChild(labelN)
            form.appendChild(nombre)
            form.appendChild(labelP)
            form.appendChild(precio)
            form.appendChild(labelD)
            form.appendChild(detalles)
            form.appendChild(actualizarBtn)
            form.appendChild(volverBtn)
        }catch (e){
            console.error(e)
        }
    })
    row.querySelector(".borrar").addEventListener("click",async e=>{
        const id=ser.codServicio

        try{
            let response=await fetch(`http://localhost:8080/servicios/buscar/${id}`)
            if (response.status===404){
                alert("El id ingresado no esta registrado")
                return
            }
            if (!response.ok){
                throw new Error("Error al buscar el servicio")
            }
            if (confirm("Seguro que desea eliminar el servicio?")) {
                let res = await fetch(`http://localhost:8080/servicios/borrar/${id}`, {
                    method: "DELETE"
                })
                if (!res.ok) {
                    throw new Error("Error al borrar el servicio")
                }
                alert("Servicio eliminado")
                window.location.reload()
            }else{
                alert("Operacion cancelada")
            }
        }catch (e){
            console.error(e)
        }
    })
    return row
}
async function mostrar(){
    try{
        let res=await fetch("http://localhost:8080/servicios/mostrar")
        if (!res.ok){
            throw new Error("Error al obtener los servicios")
        }
        let servicios=await res.json()
        servicios.slice(0,10).forEach(ser=>{
            let row=crearFila(ser)
            tabla.appendChild(row)
        })
    }catch (e){
        console.error(e)
    }
}
document.addEventListener("DOMContentLoaded",mostrar)
document.getElementById("id").addEventListener("input",async e=>{
    e.preventDefault()

    const id=e.target.value
    tabla.innerHTML=""
    let p=document.getElementById("info-servicio")
    try{
        if (id===""){
            mostrar()
            return
        }
        let res=await fetch(`http://localhost:8080/servicios/buscar/${id}`)
        if (res.status===404){
            p.textContent="El servicio no se encontro"
            p.style.color="red"
            return
        }
        if (!res.ok){
            throw new Error("Error al buscar el servicio")
        }
        let servicio=await res.json()
        let row=crearFila(servicio)
        tabla.appendChild(row)
        p.textContent=""
    }  catch (e){
        console.error(e)
    }
})