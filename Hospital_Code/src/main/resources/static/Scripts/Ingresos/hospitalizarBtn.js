const form=document.querySelector("form")
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
        let res=await fetch("http://localhost:8080/ingresos/guardar",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify({
                idIngreso: ingreso.idIngreso,
                acompañante: ingreso.acompañante,
                hospitalizado: true,
                persona: per
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