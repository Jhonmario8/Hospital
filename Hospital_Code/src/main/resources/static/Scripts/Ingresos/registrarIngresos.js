const form=document.querySelector("form")
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
    const acompañante=document.getElementById("acompañante").value

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
        if (per.citas.length===0){
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
                    persona:per,
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