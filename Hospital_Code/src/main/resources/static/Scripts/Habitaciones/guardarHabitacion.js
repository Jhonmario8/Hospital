const guardarBtn=document.getElementById("guardarBtn")
const form=document.querySelector("form")
guardarBtn.addEventListener("click",async e=>{
    e.preventDefault()
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    const tipo=document.getElementById("tipo").value
    const capacidad=document.getElementById("capacidad").value

    try{
        let res=await fetch("http://localhost:8080/habitaciones/guardar",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify({
                tipoHabitacion: tipo,
                capacidad: capacidad
            })
        })
        if (!res.ok){
            throw new Error("Error al guardar la habitacion")
        }
        let json=await res.json()
        alert(`Habitacion creada con exito, ID asignado: ${json.numHabitacion}`)
    }catch (e){{
        console.error(e)
    }}
})