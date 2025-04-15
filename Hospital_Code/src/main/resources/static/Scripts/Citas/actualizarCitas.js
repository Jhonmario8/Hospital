function crearInput(id, labelText, value) {
  const label = document.createElement("label");
  label.setAttribute("for", id);
  label.textContent = labelText;

  const input = document.createElement("input");
  input.setAttribute("id", id);
  input.setAttribute("required", true);
  input.value = value;

  return [label, input];
}

document.getElementById("buscarBtn").addEventListener("click",async e=>{
  e.preventDefault()
  const id=document.getElementById("id").value
    try{
      let res=await fetch(`http://localhost:8080/personas/buscar/${id}`)
        if (res.status===404){
            alert("No se encontro el paciente")
            return
        }
        let persona=await res.json()
        let cita=persona.citas.find(()=>true)
        if (!cita){
          alert("No esta persona no tiene citas agendadas")
        }
        console.log(cita)
        let form=document.getElementById("form")
        form.innerHTML=""

      let [labelF,fecha]=crearInput("fecha","Fecha: ",cita.fechaCita)
      fecha.setAttribute("type","date")
      let [labelH,hora]=crearInput("hora","Hora: ",cita.horaCita)
      hora.setAttribute("type","time")
      let [labelM,motivo]=crearInput("motivo","Motivo: ",cita.motivo)

      const actualizarBtn=document.createElement("button")
      actualizarBtn.textContent="Actualizar"
      const volverBtn=document.createElement("a")
      volverBtn.setAttribute("href","../../html/GestionCitas/actualizacita.html")
      volverBtn.setAttribute("class","button")
      volverBtn.textContent="Volver"

      actualizarBtn.addEventListener("click",async e=>{
        e.preventDefault()

        try{
          let response=await fetch(`http://localhost:8080/citas/actualizar/${cita.idCita}/persona/${id}`,{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify({

              fechaCita: fecha.value,
              horaCita: hora.value,
              motivo: motivo.value
            })
          })
          if (!response.ok){
            throw new Error("Error al actualizar la cita")
          }
          alert("Cita actualizada con exito")
          window.location.href="../../html/GestionCitas/actualizacita.html"
        }catch (e){
          console.error(e)
        }
      })

      form.appendChild(labelF)
      form.appendChild(fecha)
      form.appendChild(labelH)
      form.appendChild(hora)
      form.appendChild(labelM)
      form.appendChild(motivo)
      form.appendChild(actualizarBtn)
      form.appendChild(volverBtn)

    }catch (e){
      console.error(e)
    }
})