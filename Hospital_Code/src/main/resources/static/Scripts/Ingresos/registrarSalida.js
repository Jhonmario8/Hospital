const form = document.querySelector("form")
const div = document.getElementById("div")
const tbody = document.getElementById("tbody")
const info = document.getElementById("info")
document.getElementById("id").addEventListener("input", async e => {
    e.preventDefault()
    div.style.display = "block"
    const idPac = e.target.value
    tbody.innerHTML = ""
    info.textContent = ""
    if (idPac === "") {
        div.style.display = "none"
        return
    }
    try {
        let res = await fetch(`http://localhost:8080/personas/containing/${idPac}`)
        if (res.status === 404) {
            let mensaje = await res.text()
            info.textContent = mensaje
            info.style.color = "red"
            div.style.display = "none"
            return
        }
        if (!res.ok) {
            throw new Error("Error al obtener los pacientes")
        }
        let personas = await res.json()
        let pacientes = personas.filter(p => !p.tipoPersona)
        pacientes.forEach(pac => {
            let row = document.createElement("tr")
            row.innerHTML = `
            <td>${pac.idPersona}</td>
            <td>${pac.nomPersona}</td>
            <td>${pac.edadPersona}</td>
            <td>${pac.telefonoPersona}</td>
            `
            row.addEventListener("click", ev => {
                ev.preventDefault()
                e.target.value = pac.idPersona
                tbody.innerHTML = row.innerHTML
            })
            tbody.appendChild(row)
        })
    } catch (e) {
        console.error(e)
    }
})
document.getElementById("registrarBtn").addEventListener("click", async e => {
    e.preventDefault()
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    const id = document.getElementById("id").value
    let pago = document.getElementById("total")
    let p = document.getElementById("servicios")
    try {
        let res = await fetch(`http://localhost:8080/personas/buscar/${id}`)
        if (res.status === 404) {
            alert("La persona no se encontro")
        }
        if (!res.ok) {
            throw new Error("Error al buscar la persona")
        }
        let per = await res.json()
        if (per.tipoPersona) {
            alert("Ingrese un paciente")
        }
        let resIng = await fetch(`http://localhost:8080/ingresos/buscarPaciente/${id}`)
        if (resIng.status === 404) {
            alert("El paciente no ha sido ingresado")
            return
        }
        if (!resIng.ok) {
            throw new Error("Error al buscar el ingreso")
        }
        if (confirm("¿Deseas registrar la salida del paciente?")) {

            let reser = await fetch(`http://localhost:8080/personaas/getServicios/${per.idPersona}`)
            if (!reser.ok) {
                throw new Error("Error la obtener los servicios")
            }
            let servicios = await reser.json()
            if (servicios.length === 0) {
                p.textContent = "No hay"
            } else {
                servicios.forEach(ser => {
                    p.innerHTML += `______________________<br>
            ${ser.nomServicio}<br>
            ${ser.precioServicio} $<br>
`
                })
            }

            let response = await fetch(`http://localhost:8080/servicios/cuenta/${id}`)
            if (!response.ok) {
                throw new Error("Error al obtener la cuenta")
            }
            let cuenta = await response.json()
            pago.textContent = cuenta.toFixed(2)
            document.getElementById("pay").style.display = "block"
            document.getElementById("ser").style.display = "block"


            let ingreso = await resIng.json()
            let respuesta = await fetch(`http://localhost:8080/ingresos/borrar/${ingreso.idIngreso}`, {
                method: "DELETE"
            })
            if (!respuesta.ok) throw new Error("Error al borrar el ingreso")
            alert("Salida registrada")
        }
    } catch (e) {
        console.error(e)
    }
})