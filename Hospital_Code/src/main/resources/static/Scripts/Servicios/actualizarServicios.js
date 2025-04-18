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

document.getElementById("buscarBtn").addEventListener("click",async e=>{
    if (!form.checkValidity()) {
        return;
    }

    e.preventDefault()

    const id=document.getElementById("id").value
    let form=document.getElementById("form")
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
        volverBtn.setAttribute("href","../../html/GestionServicios/actualizaservicio.html")
        volverBtn.setAttribute("class","button")

        actualizarBtn.addEventListener("click",async e=>{
            e.preventDefault()
            try {
                let res = await fetch(`http://localhost:8080/servicios/guardar`, {
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
                window.location.href = "../../html/GestionServicios/actualizaservicio.html"
            }catch (e){
                console.error(e)
            }
        })
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