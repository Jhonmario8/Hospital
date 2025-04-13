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
            row.innerHTML=`
            <td>${cita.idCita}</td>
            <td>${cita.fechaCita}</td>
            <td>${horaFormateada}</td>
            <td>${cita.motivo}</td>
            `
            let cad=""
            if (Array.isArray(cita.personas)){
                let i=0
                for (per of cita.personas){

                    if (per.tipoPersona){
                        if (i===cita.personas.length-1){
                            cad+="ID: "+per.idPersona
                            continue
                        }
                        cad+="ID: "+per.idPersona+" , "
                    }
                    i++
                }
                row.innerHTML+=`
                <td>${cita.personas.find(p=>!p.tipoPersona).idPersona}</td>
                <td>${cad}</td>
                `
            }
            tabla.appendChild(row)
        })
    }catch (e){
        console.error(e)
    }
})