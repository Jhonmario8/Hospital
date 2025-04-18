document.getElementById("guardarBtn").addEventListener("click",async e=>{
    if (!form.checkValidity()) {
        return;
    }

    e.preventDefault()

    const nombre=document.getElementById("nombre").value
    const cantidad=parseInt(document.getElementById("cantidad").value)
    const descripcion=document.getElementById("descripcion").value

    try{
        let res=await fetch("http://localhost:8080/articulos/guardar",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify({
                nomArticulo: nombre,
                cantidad: cantidad,
                descripcion: descripcion
            })
        })
        if (!res.ok){
            throw new Error("Error al guardar el articulo")
        }
        let response=await fetch("http://localhost:8080/articulos/mostrar")
        let articulos=await response.json()
        let idArt=articulos.at(articulos.length-1).idArticulo
        alert(`Articulo guardado con exito, ID asignado: ${idArt}`)
    }catch (e){
        console.error(e)
    }
})