const buscarBtn=document.getElementById("buscarBtn")

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

buscarBtn.addEventListener("click",async e=>{
    if (!form.checkValidity()) {
        return;
    }

    e.preventDefault()

    const id=document.getElementById("id").value

    try{
        let response= await fetch(`http://localhost:8080/habitaciones/buscar/${id}`)
        if (response.status===404){
            alert("El id ingresado no esta registrado")
            return
        }
        if (!response.ok){
            throw new Error("Error al buscar la habitacion")
        }
        let json=await response.json()
        let form=document.getElementById("form")
        form.innerHTML=""
        let [labelT,tipo]=crearInput("tipo","Tipo: ",json.tipoHabitacion)
        let [labelC,capacidad]=crearInput("capacidad","Capacidad: ",json.capacidad)

        const actualizarBtn=document.createElement("button")
        actualizarBtn.textContent="Actualizar"

        const volverBtn=document.createElement("a")
        volverBtn.textContent="Volver"
        volverBtn.setAttribute("class","button")
        volverBtn.setAttribute("href","../../html/GestionHabitaciones/actualizahabitacion.html")
        actualizarBtn.addEventListener("click",async e=>{
            e.preventDefault()
            try {
                let res=await fetch("http://localhost:8080/habitaciones/actualizar",{
                    method:"PUT",
                    headers:{"Content-Type":"application/json"},
                    body:JSON.stringify({
                        numHabitacion: id,
                        tipoHabitacion: tipo.value,
                        capacidad: capacidad.value
                    })
                })
                if (!res.ok){
                    throw new Error("Error al actualizar la habitacion")
                }
                alert("Habitacion actualizada correctamente")
                window.location.href="../../html/GestionHabitaciones/actualizahabitacion.html"
            }catch (e){
                console.error(e)
            }
        })

        form.appendChild(labelT)
        form.appendChild(tipo)
        form.appendChild(labelC)
        form.appendChild(capacidad)
        form.appendChild(actualizarBtn)
        form.appendChild(volverBtn)

    }catch (e){
        console.error(e)
    }
})