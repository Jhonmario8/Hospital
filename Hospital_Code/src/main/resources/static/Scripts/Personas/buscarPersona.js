const boton=document.getElementById("buscarBtn")

boton.addEventListener("click",async e=>{
    e.preventDefault()
    const id=document.getElementById("id").value
    const info= document.getElementById("info")
    try {
        let res = await fetch(`http://localhost:8080/personas/buscar/${id}`)
        if (res.status===404){
            alert("La persona no se encontro")
            return
        }
        if (!res.ok) {
            throw new Error("Error al obtener la persona")
        }
        let json = await res.json()
        info.innerHTML = `Nombre: ${json.nomPersona}<br> Edad: ${json.edadPersona} <br> Direccion: ${json.direccion} <br> Telefono: ${json.telefonoPersona} <br> Tipo: ${json.tipoPersona ? "Empledo" : "Paciente"}`
    }catch (e){
        console.error(e)
    }
})