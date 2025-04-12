const boton=document.getElementById("guardarBtn")

boton.addEventListener("click",async e=>{
    e.preventDefault()
    const id=document.getElementById("id").value
    const nombre=document.getElementById("nombre").value
    const edad=document.getElementById("edad").value
    const direccion=document.getElementById("direccion").value
    const telefono=document.getElementById("telefono").value
    const tipo=document.getElementById("tipo").value

    try{
        let response=await fetch(`http://localhost:8080/personas/buscar/${id}`)
        if (response.ok){
            alert("Este id ya fue registrado")
            return
        }
        let res=await fetch("http://localhost:8080/personas/guardar",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify({idPersona: id,
                nomPersona: nombre,
                edadPersona: edad,
                direccion: direccion,
                telefonoPersona: telefono,
                tipoPersona: tipo
            })
        })
        if (!res.ok){
            throw new Error("Error al guardar la persona")
        }
        alert("Persona guardada con exito!!")
    }catch (e){
        console.error(e)
    }
})