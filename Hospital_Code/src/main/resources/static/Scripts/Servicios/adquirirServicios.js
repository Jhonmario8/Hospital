const tbody=document.getElementById("tbody")
const form=document.querySelector("form")
const div=document.getElementById("div")
const info=document.getElementById("info")
document.addEventListener("DOMContentLoaded",()=>{
    let idServicio=localStorage.getItem("idServicio")
    localStorage.removeItem("idServicio")

document.getElementById("idPaciente").addEventListener("input",async e=>{
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

document.getElementById("adquirirBtn").addEventListener("click",async e=>{
    e.preventDefault()
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    const id=document.getElementById("idPaciente").value

    try{
        let response=await fetch(`http://localhost:8080/personas/buscar/${id}`)
        if (response.status===404){
            alert("El id de la persona no existe")
        }
        if (!response.ok){
            throw new Error("Error al buscar la persona")
        }
        let persona=await response.json()
        if (persona.tipoPersona){
            alert("El pacinte no esta registrado")
        }
        let resIng=await fetch(`http://localhost:8080/ingresos/buscarPaciente/${id}`)
        if (resIng.status===404){
            alert("El paciente no ha sido ingresado")
            return
        }
        if (!resIng.ok){
            throw new Error("Error al buscar el ingreso")
        }
        let res=await fetch(`http://localhost:8080/servicios/adquirir/${idServicio}/paciente/${id}`,{
            method:"POST"
        })
        if (!res.ok){
            throw new Error("Error al adquirir el servicio")
        }
        alert("servicio adquirido")
        window.location.href="../../html/Catalogo/servicios.html"
    }catch (e){
        console.error(e)
    }
})
})