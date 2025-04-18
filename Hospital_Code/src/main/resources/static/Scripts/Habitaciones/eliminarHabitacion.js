const eliminarBtn=document.getElementById("eliminarBtn")
const form=document.querySelector("form")
eliminarBtn.addEventListener("click",async e=>{
    e.preventDefault()
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    const id=document.getElementById("id").value
    try{
        let response=await fetch(`http://localhost:8080/habitaciones/buscar/${id}`)
        if (response.status===404){
            alert("La habitacion no se encontro")
            return
        }
        if (!response.ok){
            throw new Error("Error al buscar la habitacion")
        }
        let conf=confirm("Seguro que desea borrar la habitacion?")
        if (conf) {
            let res = await fetch(`http://localhost:8080/habitaciones/borrar/${id}`, {
                method: "DELETE"
            })
            if (!res.ok) {
                throw new Error("Error al borrar la habitacion")
            }
            alert("Habitacion eliminada")
            document.querySelector("form").reset()
        }
    }catch (e){
        console.error(e)
    }
})