let tabla=document.getElementById("citas-tabla")
const form=document.querySelector("form")
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
            <td class="td"><button class="editar">Editar</button> <button class="borrar">Borrar</button></td>
            `
    row.querySelector(".editar").addEventListener("click",async e=>{
        e.preventDefault()
        const id=cita.idCita
        document.querySelector("table").innerHTML=""
        try{
            let res=await fetch(`http://localhost:8080/citas/buscar/${id}`)
            if (res.status===404){
                alert("No se encontro la cita")
                return
            }
            if (!res.ok){
                throw new Error("Error al buscar la cita")
            }
            let cita=await res.json()
            form.innerHTML=""

            let [labelF,fecha]=crearInput("fecha","Fecha: ",cita.fechaCita)
            fecha.setAttribute("type","date")
            let [labelH,hora]=crearInput("hora","Hora: ",cita.horaCita)
            hora.setAttribute("type","time")
            let [labelM,motivo]=crearInput("motivo","Motivo: ",cita.motivo)

            const actualizarBtn=document.createElement("button")
            actualizarBtn.textContent="Actualizar"
            const volverBtn=document.createElement("a")
            volverBtn.setAttribute("href","../../html/GestionCitas/buscacita.html")
            volverBtn.setAttribute("class","button")
            volverBtn.textContent="Volver"

            actualizarBtn.addEventListener("click",async e=>{
                e.preventDefault()

                try{
                    let response=await fetch(`http://localhost:8080/citas/actualizar`,{
                        method:"POST",
                        headers:{"Content-Type":"application/json"},
                        body:JSON.stringify({
                            idCita: id,
                            fechaCita: fecha.value,
                            horaCita: hora.value,
                            motivo: motivo.value
                        })
                    })
                    if (!response.ok){
                        throw new Error("Error al actualizar la cita")
                    }
                    alert("Cita actualizada con exito")
                    window.location.reload()
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
    row.querySelector(".borrar").addEventListener("click",async e=>{
        e.preventDefault()
        const id=cita.idCita
        try{
            let response=await fetch(`http://localhost:8080/citas/buscar/${id}`)
            if (response.status===404){
                alert("La cita no se encontro")
                return
            }
            if (!response.ok){
                throw new Error("Error al buscar la cita")
            }
            if (confirm("Seguro que desea eliminar la cita?")) {
                let res = await fetch(`http://localhost:8080/citas/borrar/${id}`,{
                    method:"DELETE"
                })
                if (!res.ok) {
                    throw new Error("Error al borrar la cita")
                }
                alert("Cita eliminada con exito")
                window.location.reload()
            }
        }catch (e){
            console.error(e)
        }
    })
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