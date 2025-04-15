document.getElementById("eliminarBtn").addEventListener("click",async e=>{
    e.preventDefault()

    const id=document.getElementById("id").value

    try{
        let res=await fetch(`http://localhost:8080/articulos/buscar/${id}`)
        if (res.status===404){
            alert("El id ingresado no esta registrado")
            return
        }
        if (!res.ok){
            throw new Error("Error al buscar el articulo")
        }
        if (confirm("Seguro que desea eliminar el articulo?")) {
            let response = await fetch(`http://localhost:8080/articulos/borrar/${id}`, {
                method: "DELETE"
            })
            if (!response.ok) {
                throw new Error("Error al borrar el articulo")
            }
            alert("Articulo eliminado con exito")
            document.querySelector("form").reset()
        }
    }catch (e){
        console.error(e)
    }
})