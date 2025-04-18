document.getElementById("guardarBtn").addEventListener("click",async e=>{
    if (!form.checkValidity()) {
        return;
    }

    e.preventDefault()

    const fecha=document.getElementById("fecha").value
    const hora=document.getElementById("hora").value
    const motivo=document.getElementById("motivo").value
    const idPaciente=parseInt(document.getElementById("idPaciente").value)

    try{
        let response=await fetch("http://localhost:8080/persona/pacientes")
        let pacientes=await response.json()
        let persona = pacientes.find(p => p.idPersona === idPaciente && !p.tipoPersona);
        if (!persona){
            alert("El idPaciente no existe")
            return
        }
        if (persona.citas.find(()=>true)){
            alert("Esta persona ya tiene una cita agendada")
            return
        }
        let res=await fetch(`http://localhost:8080/citas/guardar/${idPaciente}`,{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify({
                fechaCita: fecha,
                horaCita: hora,
                motivo: motivo
            })
        })
        if (!res.ok){
            throw new Error("Error al guardar la cita")
        }
        alert("Cita agendada con exito")
        let form=document.getElementById("form")
        form.innerHTML=""
        const labelI=document.createElement("label")
        labelI.setAttribute("for","id")
        labelI.textContent="ID Empleado: "
        const idEmp=document.createElement("input")
        idEmp.setAttribute("id","id")
        idEmp.setAttribute("required",true)

        const asignarBtn=document.createElement("button")
        asignarBtn.textContent="Asignar"

        asignarBtn.addEventListener("click",async e=>{
          e.preventDefault()
          try{
              let rsp=await fetch(`http://localhost:8080/citas/mostrar`)
              let json=await rsp.json()
              let cita=json.at(json.length-1)
              let respuesta=await fetch(`http://localhost:8080/citas/guardar/${idEmp.value}`,{
                  method:"POST",
                  headers:{"Content-Type":"application/json"},
                  body:JSON.stringify({
                      idCita: cita.idCita,
                      fechaCita: fecha,
                      horaCita: hora,
                      motivo: motivo,
                  })
              })
              if (!respuesta.ok){
                  throw new Error("Error al asignar el empleado")
              }
              alert("Empleado asignado")
              window.location.href="../../html/GestionCitas/guardacita.html"
          }catch (e){
              console.error(e)
          }

        })
        form.appendChild(labelI)
        form.appendChild(idEmp)
        form.appendChild(asignarBtn)
    }catch (e){
        console.error(e)
    }
})