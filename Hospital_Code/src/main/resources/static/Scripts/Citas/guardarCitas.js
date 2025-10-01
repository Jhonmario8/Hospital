const form = document.querySelector("form")

document.getElementById("guardarBtn").addEventListener("click", async e => {
    e.preventDefault()
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    const fecha = document.getElementById("fecha").value
    let hora = document.getElementById("hora").value
    const motivo = document.getElementById("motivo").value
    const idPaciente = parseInt(document.getElementById("idPaciente").value)
    try {
        let response = await fetch("http://localhost:8080/persona/pacientes")
        let pacientes = await response.json()
        let persona = pacientes.find(p => p.idPersona === idPaciente && !p.tipoPersona);
        if (!persona) {
            alert("El idPaciente no existe")
            return
        }
        let res = await fetch(`http://localhost:8080/citas/guardar/${idPaciente}`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({
                fechaCita: fecha,
                horaCita: hora,
                motivo: motivo
            })
        })
        if (!res.ok) {
            throw new Error("Error al guardar la cita")
        }
        alert("Cita agendada con exito")
        let form = document.getElementById("form")
        form.innerHTML = ""
        const labelI = document.createElement("label")
        labelI.setAttribute("for", "id")
        labelI.textContent = "ID Empleado: "
        const idEmp = document.createElement("input")
        idEmp.setAttribute("id", "id")
        idEmp.setAttribute("required", true)
        const tabla=document.createElement("table")
        tabla.setAttribute("class","styled-table")
        tabla.style.display="none"
        const info=document.createElement("p")
        tabla.innerHTML=`
        <thead>
              <th>Documento</th>
              <th>Nombre</th>
             <th>Edad</th>
        </thead>
        <tbody></tbody>
        `
        idEmp.addEventListener("input", async e => {
            e.preventDefault()
            const id = e.target.value
            const tbody = tabla.querySelector("tbody")
            tabla.style.display="block"
            info.textContent=""
            tbody.innerHTML = ""
            try {
                if (id===""){
                    tabla.style.display="none"
                    return
                }
                let res = await fetch(`http://localhost:8080/personas/containing/${id}`)
                if (res.status===404){
                    tabla.style.display="none"
                    info.textContent="No se encontro ningun empleado"
                    info.style.color="red"
                }
                if (!res.ok) {
                    throw new Error("Error al buscar las personas")
                }
                let personas = await res.json();
                let medicos = personas.filter(p => p.tipoPersona)
                medicos.forEach(m => {
                    let row = document.createElement("tr")
                    row.innerHTML = `
              <td>${m.idPersona}</td>
              <td>${m.nomPersona}</td>
              <td>${m.edadPersona}</td>
        `
                    row.addEventListener("click", ()=>{
                        e.target.value=m.idPersona
                        tabla.style.display="none"
                    })
                    tbody.appendChild(row)
                })
            } catch (e) {
                console.error(e)
            }
        })

        const asignarBtn = document.createElement("button")
        asignarBtn.textContent = "Asignar"
        asignarBtn.style.marginTop="10px"

        asignarBtn.addEventListener("click", async e => {
            e.preventDefault()
            try {
                let rsp = await fetch(`http://localhost:8080/citas/mostrar`)
                let json = await rsp.json()
                let cita = json.at(json.length - 1)
                let respuesta = await fetch(`http://localhost:8080/citas/guardar/${idEmp.value}`, {
                    method: "POST",
                    headers: {"Content-Type": "application/json"},
                    body: JSON.stringify({
                        idCita: cita.idCita,
                        fechaCita: fecha,
                        horaCita: hora,
                        motivo: motivo,
                    })
                })
                if (!respuesta.ok) {
                    throw new Error("Error al asignar el empleado")
                }
                alert("Empleado asignado")
                window.location.href = "../../html/GestionCitas/guardacita.html"
            } catch (e) {
                console.error(e)
            }

        })
        form.appendChild(labelI)
        form.appendChild(idEmp)
        form.appendChild(asignarBtn)
        form.appendChild(info)
        form.appendChild(tabla)
    } catch (e) {
        console.error(e)
    }
})

document.getElementById("idPaciente").addEventListener("input", async e => {
    e.preventDefault()
    const id = e.target.value
    const tbody = document.querySelector("tbody")
    const info=document.getElementById("info")
    const table=document.querySelector("table")
    table.style.display="block"
    info.textContent=""
    tbody.innerHTML = ""
    try {
        if (id===""){
            table.style.display="none"
            return
        }
        let res = await fetch(`http://localhost:8080/personas/containing/${id}`)
        if (res.status===404){
            const mensaje=await res.text()
            table.style.display="none"
            info.textContent=mensaje
            info.style.color="red"
        }
        if (!res.ok) {
            throw new Error("Error al buscar las personas")
        }
        let personas = await res.json();
        let pacientes = personas.filter(p => !p.tipoPersona)
        pacientes.forEach(p => {
            let row = document.createElement("tr")
            row.innerHTML = `
              <td>${p.idPersona}</td>
              <td>${p.nomPersona}</td>
              <td>${p.edadPersona}</td>
        `
            row.addEventListener("click", ()=>{
                e.target.value=p.idPersona
                table.style.display="none"
            })
            tbody.appendChild(row)
        })
    } catch (e) {
        console.error(e)
    }
})