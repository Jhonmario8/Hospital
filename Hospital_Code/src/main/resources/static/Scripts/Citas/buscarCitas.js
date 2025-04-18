const form=document.querySelector("form")
document.getElementById("buscarBtn").addEventListener("click",async e=>{
    e.preventDefault()

    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    const id=document.getElementById("idPersona").value
    try{
        let response=await fetch(`http://localhost:8080/personas/buscar/${id}`)
        if (response.status===404){
            alert("El id ingresado no existe")
            return
        }
        if (!response.ok){
            throw new Error("Error al buscar la persona")
        }
        let persona=await response.json()
        let info=document.getElementById("infoCita")
        if (persona.citas.length===0){
            info.textContent="La persona no tiene citas agendadas"
            info.style.color="red"
            return
        }
        let informacion=""
        let pacId
        if (!persona.tipoPersona) {

            persona.citas.forEach(cita => {
                idEmp=cita.personas.find(p => p.tipoPersona)

                informacion += `______________________<br>
        Id: ${cita.idCita}<br>
        Fecha: ${cita.fechaCita} <br>
        Hora: ${cita.horaCita} <br>
        Motivo: ${cita.motivo} <br>
        Paciente: ${persona.idPersona}<br>
        Empleado:${idEmp?idEmp.idPersona:"No asignado"}
        
`
                info.innerHTML = informacion
                info.style.color = "white"

            })
        }
        else{
            persona.citas.forEach(cita => {

                informacion += `______________________<br>
        Id: ${cita.idCita}<br>
        Fecha: ${cita.fechaCita} <br>
        Hora: ${cita.horaCita} <br>
        Motivo: ${cita.motivo} <br>
        Paciente: ${cita.personas.find(p => !p.tipoPersona).idPersona}<br>
        Empleado:${persona.idPersona}
        
`
                info.innerHTML = informacion
                info.style.color = "white"


            })
        }

    }catch (e){
        console.error(e)
    }
})