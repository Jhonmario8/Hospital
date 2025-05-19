const boton=document.getElementById("guardarBtn")
const form=document.querySelector("form")
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
document.getElementById("telefono").addEventListener("input",async e=>{
    const num=e.target.value
    let message=document.getElementById("lengthMsg")
    if (num.length!==10){
        message.textContent="Ingrese un numero valido"
        message.style.color="red"
        message.style.fontSize="12px"
    }
    else{
        message.textContent=""
    }
})

boton.addEventListener("click",async e=>{
    e.preventDefault()
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    const id=parseInt( document.getElementById("id").value)
    const nombre= document.getElementById("nombre").value
    const edad=parseInt(document.getElementById("edad").value)
    const direccion=document.getElementById("direccion").value
    const telefono=document.getElementById("telefono").value
    let tipo=document.getElementById("tipo").value === "true"
    try{
        if(telefono.length!=10){
            alert("Ingrese un numero valido")
            throw new Error("Error: envie un numero valido")
        }
        let response=await fetch( `http://localhost:8080/personas/inactivo/${id}`)
        if (!response.ok && response.status!==404){
            throw new Error("Error al buscar la persona")
        }
        if (response.status!==404) {
            let persona = await response.json()

        if (persona!==null) {
            if (confirm("Persona inactiva, desea activarla?")) {
                let respuesta = await fetch(`http://localhost:8080/personas/activar/${id}`, {
                    method: "POST"
                })
                if (!respuesta.ok) {
                    throw new Error("Error al activar la persona")
                }
                alert("Persona activada exitosamente")
                return
            }
        }
        }
        let res=await fetch("http://localhost:8080/personas/guardar",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify({
                idPersona: id,
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