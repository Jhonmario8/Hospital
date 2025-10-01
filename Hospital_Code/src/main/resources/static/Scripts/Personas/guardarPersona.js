const boton=document.getElementById("guardarBtn")
const form=document.querySelector("form")
document.getElementById("id").addEventListener("input",async e=>{
    let id=e.target.value
    let mensaje=document.getElementById("existeMensaje")
    if (id.length>=10){
       e.target.value=id.slice(0,10)
    }
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
document.getElementById("telefono").addEventListener("input",e=>{
    if (e.target.value.length>10){
        e.target.value=e.target.value.slice(0,10)
    }
})

boton.addEventListener("click",async e=>{
    e.preventDefault()



    const id=parseInt( document.getElementById("id").value)
    const nombre= document.getElementById("nombre").value
    const edad=parseInt(document.getElementById("edad").value)
    const direccion=document.getElementById("direccion").value
    const telefono=document.getElementById("telefono").value
    let tipo=document.getElementById("tipo").value === "true"
    let tel=document.getElementById("telefono")

    if (tel.value.length<10){
        tel.setCustomValidity("Ingrese un numero de 10 digitos")
        form.reportValidity()
    }
    else {
        tel.setCustomValidity("")
    }
    if (!form.checkValidity()) {
        form.reportValidity();
        return
    }

    try{
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
        let respuesta=await fetch(`http://localhost:8080/personas/buscar/${id}`)
        if (!respuesta.ok && respuesta.status!==404){
            throw new Error("Error al buscar la persona")
        }
        if (respuesta.status!==404){
            alert("Ya hay una persona registrada con ese id")
            return
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