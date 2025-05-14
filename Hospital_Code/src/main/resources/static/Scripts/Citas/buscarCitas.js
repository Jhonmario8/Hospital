let tabla=document.getElementById("citas-tabla")
function crearFila(cita){
    let hora = new Date(`1970-01-01T${cita.horaCita}`);
    let horaFormateada = hora.toLocaleTimeString("es-CO", {
        hour: '2-digit',
        minute: '2-digit',
        hour12: true
    });
    let row= document.createElement("tr")
    let paciente = cita.personas.find(p => !p.tipoPersona);
    let empleado = cita.personas.find(p => p.tipoPersona);
    row.innerHTML=`
            <td>${cita.idCita}</td>
            <td>${cita.fechaCita}</td>
            <td>${horaFormateada}</td>
            <td>${cita.motivo}</td>
            <td>${paciente ? paciente.idPersona : "No hay"}: ${paciente.nomPersona}</td>
            <td>${empleado ? empleado.idPersona : "No hay"}: ${empleado.nomPersona}</td>
            `
    return row
}
async function mostrar(){
    try{
        let res=await fetch("http://localhost:8080/citas/mostrar")
        if (!res.ok){
            throw new Error("Error al obtener las citas")
        }
        let citas=await res.json()
        tabla.innerHTML = ""
        citas.slice(0,10).forEach(cita=>{
            let row=crearFila(cita)
            tabla.appendChild(row)
        })
    }catch(e){
        console.error(e)
    }
}
document.addEventListener("DOMContentLoaded",mostrar)
document.getElementById("id").addEventListener("input",async e=>{
    e.preventDefault()

    tabla.innerHTML=""
    const id=e.target.value

    let info=document.getElementById("infoCita")
    try{
        if (id===""){
            mostrar()
            return
        }
        let res=await fetch(`http://localhost:8080/citas/buscar/${id}`)
        if (res.status===404){
            info.textContent="La cita no se encontro"
            info.style.color="red"
            return
        }
        if (!res.ok){
            throw new Error("Error al buscar la cita")
        }
        let cita=await res.json()
        console.log("Cita encontrada:", cita); // Añadir para depuración
        let row=crearFila(cita)
        tabla.appendChild(row)
        info.textContent=""

    }catch (e){
        console.error(e)
    }
})