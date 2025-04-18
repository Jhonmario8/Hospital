const form=document.querySelector("form")
document.getElementById("eliminarBtn").addEventListener("click",async e=>{
    e.preventDefault()
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    const id=document.getElementById("id").value

    try{
        let response=await fetch(`http://localhost:8080/servicios/buscar/${id}`)
        if (response.status===404){
            alert("El id ingresado no esta registrado")
            return
        }
        if (!response.ok){
            throw new Error("Error al buscar el servicio")
        }
        if (confirm("Seguro que desea eliminar el servicio?")) {
            let res = await fetch(`http://localhost:8080/servicios/borrar/${id}`, {
                method: "DELETE"
            })
            if (!res.ok) {
                throw new Error("Error al borrar el servicio")
            }
            alert("Servicio eliminado")
            document.querySelector("form").reset()
        }else{
            alert("Operacion cancelada")
        }
    }catch (e){
        console.error(e)
    }
})