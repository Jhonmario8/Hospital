document.getElementById("eliminarBtn").addEventListener("click",async e=>{
    e.preventDefault()

    const id=document.getElementById("id").value
    try{
        let response=await fetch(`http://localhost:8080/citas/buscar/${id}`)
        if (response.status===404){
            alert("La cita no se encontro")
            return
        }
        if (!response.ok){
            throw new Error("Error al buscar la cita")
        }
        if (confirm("Seguro que desea eliminar la cita?")) {
            let res = await fetch(`http://localhost:8080/citas/borrar/${id}`,{
                method:"DELETE"
            })
            if (!res.ok) {
                throw new Error("Error al borrar la cita")
            }
            alert("Cita eliminada con exito")
            document.querySelector("form").reset()
        }
    }catch (e){
        console.error(e)
    }
})