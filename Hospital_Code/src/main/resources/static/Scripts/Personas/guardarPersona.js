const boton=document.getElementById("guardarBtn")

document.getElementById("id").addEventListener("input",async e=>{
    const id=e.target.value
    let mensaje=document.getElementById("existeMensaje")
    try {
        let respuesta=await fetch(`http://localhost:8080/personas/buscar/${id}`)
        if (respuesta.ok){
            mensaje.textContent="Este id ya fue registrado"
            mensaje.style.color="red"
            mensaje.style.fontSize="12px"
        }
        else{
            mensaje.textContent=""
        }
    }catch (e){
    }
})

boton.addEventListener("click",async e=>{
    e.preventDefault()
    const id=document.getElementById("id").value
    const nombre=document.getElementById("nombre").value
    const edad=document.getElementById("edad").value
    const direccion=document.getElementById("direccion").value
    const telefono=document.getElementById("telefono").value
    const tipo=document.getElementById("tipo").value

    try{
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