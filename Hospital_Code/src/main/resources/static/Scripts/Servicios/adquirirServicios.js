
document.addEventListener("DOMContentLoaded",()=>{
    let idServicio=localStorage.getItem("idServicio")
    localStorage.removeItem("idServicio")


document.getElementById("adquirirBtn").addEventListener("click",async e=>{
    if (!form.checkValidity()) {
        return;
    }

    e.preventDefault()

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