const eliminarBtn=document.getElementById("eliminarBtn")
const form=document.querySelector("form")
eliminarBtn.addEventListener("click",async e=>{
    e.preventDefault()
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    const id=document.getElementById("id").value
    try {
        let response= await fetch(`http://localhost:8080/personas/buscar/${id}`)
        if (response.status===404){
            alert("El id ingresado no esta registrado")
            return
        }
        if (!response.ok){
            throw  new Error("Error al buscar la persona")
        }
        let conf=confirm("Estas seguro que deseas eliminar la persona?")
        if (conf) {
            let res = await fetch(`http://localhost:8080/personas/borrar/${id}`,{
                method:"DELETE"
            })
            if (!res.ok){
                throw new Error("Error al elimina la persona")
            }
            alert("Persona eliminada correctamente")
            document.querySelector("form").reset()
        }
    }catch (e){
        console.error(e)
    }
})