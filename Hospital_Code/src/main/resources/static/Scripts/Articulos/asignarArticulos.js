document.getElementById("asignarBtn").addEventListener("click",async e=>{
    if (!form.checkValidity()) {
        return;
    }

    e.preventDefault()
    const idArt=document.getElementById("idArt").value
    const idHab=document.getElementById("idHab").value
    const cantidad=document.getElementById("cantidad").value

    try{
        let res=await fetch(`http://localhost:8080/articulos/buscar/${idArt}`)
        if (res.status===404){
            alert("El id del articulo no se encontro")
        }
        if (!res.ok){
            throw new Error("Error al buscar el articulo")
        }
        let respuesta= await fetch(`http://localhost:8080/habitaciones/buscar/${idHab}`)
        if (respuesta.status===404){
            alert("El id de la habitacion no se encontro")
        }
        if (!respuesta.ok){
            throw new Error("Error al buscar la habitacion")
        }

        let response=await fetch(`http://localhost:8080/articulos/asignar/${idArt}/habitacion/${idHab}/cantidad/${cantidad}`,{
            method:"POST"
        })
        let json=await response.json()

        if (json) {

            if (!response.ok) {
                throw new Error("Error al asignar el articulo")
            }
            alert("articulo asignado con exito")
        }else {
            alert("No hay suficietes articulos")
        }
    }catch (e){
        console.error(e)
    }
})