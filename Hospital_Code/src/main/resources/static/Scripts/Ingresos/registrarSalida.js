const form=document.querySelector("form")
document.getElementById("registrarBtn").addEventListener("click",async e=>{
    e.preventDefault()
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    const id=document.getElementById("id").value
    let pago=document.getElementById("total")
    let p=document.getElementById("servicios")
    try{
        let res=await fetch(`http://localhost:8080/personas/buscar/${id}`)
        if (res.status===404){
            alert("La persona no se encontro")
        }
        if (!res.ok){
            throw new Error("Error al buscar la persona")
        }
        let per=await res.json()
        if (per.tipoPersona){
            alert("Ingrese un paciente")
        }
        let resIng=await fetch(`http://localhost:8080/ingresos/buscarPaciente/${id}`)
        if (resIng.status===404){
            alert("El paciente no ha sido ingresado")
            return
        }
        if (!resIng.ok){
            throw new Error("Error al buscar el ingreso")
        }
        let response=await fetch(`http://localhost:8080/servicios/cuenta/${id}`)
        if (!response.ok){
            throw new Error("Error al obtener la cuenta")
        }
        document.querySelectorAll("h3").forEach(h=>h.style.display="block")
        let cuenta=await response.json()
        pago.textContent=cuenta.toFixed(2)

        per.servicios.forEach(ser=>{
            p.innerHTML+=`______________________<br>
            ${ser.nomServicio}<br>
            ${ser.precioServicio} $<br>
`
        })

        let ingreso=await resIng.json()
        let respuesta=await fetch(`http://localhost:8080/ingresos/borrar/${ingreso.idIngreso}`,{
            method:"DELETE"
        })
        if (!respuesta.ok){
            throw new Error("Error al borrar el ingreso")
        }
        alert("Salida registrada")
    }catch (e){
        console.error(e)
    }
})