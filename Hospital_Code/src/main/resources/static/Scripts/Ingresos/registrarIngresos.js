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
document.getElementById("registrarBtn").addEventListener("click",async e=>{
    e.preventDefault()
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    const btn = document.getElementById("registrarBtn");
    btn.disabled = true;
    btn.textContent = "Registrando...";

    const id=document.getElementById("id").value
    const acompañante=document.getElementById("acompañante").value==="true"

    try{
       let resPcs=await fetch(`http://localhost:8080/personas/buscar/${id}`)
        if (resPcs.status===404){
            alert("La persona no se encuentra registrada")
            btn.disabled = false;
            btn.textContent = "Registrar";
            return
        }
        if (!resPcs.ok){
            throw new Error("Error al buscar el paciente")
        }
        let per=await resPcs.json()
        if (per.tipoPersona){
            alert("El paciente no se encontro")
            btn.disabled = false;
            btn.textContent = "Registrar";
            return
        }
        let respuesta=await fetch(`http://localhost:8080/personas/tieneCita/${per.idPersona}`)
        if (!respuesta.ok){
            throw new Error("Error al validar si tiene citas")
        }
        let tieneCita=await respuesta.json()
        if (!tieneCita){
            alert("El paciente no tiene citas agendadas")
            btn.disabled = false;
            btn.textContent = "Registrar";
            return
        }
        let res=await fetch(`http://localhost:8080/ingresos/buscarPaciente/${id}`)
        if (res.status===404){
            let response=await fetch("http://localhost:8080/ingresos/guardar",{
                method:"POST",
                headers:{"Content-Type":"application/json"},
                body:JSON.stringify({
                    idPersona:per.idPersona,
                    acompañante: acompañante
                })
            })
            if (!response.ok){
                throw new Error("Error al registrar el ingreso")
            }
            alert("ingreso registrado")
            document.querySelector("form").reset()
            btn.disabled = false;
            btn.textContent = "Registrar";
            return
        }
        else{
            alert("El paciente ya fue ingresado")
        }
        if (!res.ok){
            throw new Error("Error al buscar el ingreso")
        }

    }catch (e){
        console.error(e)
    }
    btn.disabled = false;
    btn.textContent = "Registrar";
})