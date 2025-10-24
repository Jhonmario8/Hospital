document.addEventListener("DOMContentLoaded",async ()=>{
    try{
        let res=await fetch("http://localhost:8080/citas/mostrar")
        if (!res.ok){
            throw new Error("Error al obtener las citas")
        }
        let citas=await res.json()
        let tabla = document.getElementById("tabla-citas")
        citas.forEach(cita=>{
            let hora = new Date(`1970-01-01T${cita.horaCita}`);
            let horaFormateada = hora.toLocaleTimeString("es-CO", {
                hour: '2-digit',
                minute: '2-digit',
                hour12: true
            });
            let row= document.createElement("tr")
            let paciente=cita.personas.find(p=>!p.tipoPersona)
            let empleado=cita.personas.find(p=>p.tipoPersona)
            row.innerHTML=`
            <td>${cita.idCita}</td>
            <td>${cita.fechaCita}</td>
            <td>${horaFormateada}</td>
            <td>${cita.motivo}</td>
            <td>${paciente.nomPersona}: ${paciente.idPersona}</td>
            <td>${empleado.nomPersona}: ${empleado.idPersona}</td>
            `
            tabla.appendChild(row)
        })
    }catch (e){
        console.error(e)
    }
})