const form=document.querySelector("form")
const div=document.getElementById("div")
const tbody=document.getElementById("tbody")
const info=document.getElementById("info")
document.getElementById("id").addEventListener("input",async e=>{
    e.preventDefault()
    div.style.display="block"
    const idPac=e.target.value
    tbody.innerHTML=""
    info.textContent=""
    if (idPac===""){
        div.style.display="none"
        return
    }
    try{
        let res=await fetch(`http://localhost:8080/personas/containing/${idPac}`)
        if (res.status===404){
            let mensaje=await res.text()
            info.textContent=mensaje
            info.style.color="red"
            div.style.display="none"
            return
        }
        if (!res.ok){
            throw new Error("Error al obtener los pacientes")
        }
        let pacientes=await res.json()
        pacientes.forEach(pac=>{
            let row=document.createElement("tr")
            row.innerHTML=`
            <td>${pac.idPersona}</td>
            <td>${pac.nomPersona}</td>
            <td>${pac.edadPersona}</td>
            <td>${pac.telefonoPersona}</td>
            `
            row.addEventListener("click",ev=>{
                ev.preventDefault()
                e.target.value=pac.idPersona
                tbody.innerHTML=row.innerHTML
            })
            tbody.appendChild(row)
        })
    }catch (e){
        console.error(e)
    }
})
document.getElementById("hospitalizarBtn").addEventListener("click",async e=>{
    e.preventDefault()
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    const id=document.getElementById("id").value
    try{
        let response=await fetch(`http://localhost:8080/personas/buscar/${id}`)
        if (response.status===404){
            alert("La persona no se encontro")
        }
        if (!response.ok){
            throw new Error("Error al buscar la persona")
        }
        let per=await response.json()
        console.log(per)
        if (per.tipoPersona){
            alert("Ingrese un paciente")
            return
        }
        let respuesta=await fetch(`http://localhost:8080/ingresos/buscarPaciente/${id}`)
        if (respuesta.status===404){
            alert("El paciente no ha sido ingresado")
            return
        }
        if (!respuesta.ok){
            throw new Error("Error al buscar el ingreso")
        }
        let ingreso=await respuesta.json()
        let res=await fetch("http://localhost:8080/ingresos/actualizar",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify({
                idIngreso: ingreso.idIngreso,
                idPersona: per.idPersona,
                acompañante: ingreso.acompañante,
                hospitalizado: true
            })
        })
        if (!res.ok){
            throw new Error("Error al actualizar el ingreso")
        }
        localStorage.setItem("idIngreso",ingreso.idIngreso)
        alert("paciente hospitalizado")
        window.location.href="../../html/Catalogo/habitaciones.html"
    }catch (e){
        console.error(e)
    }
})