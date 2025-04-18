const buscarBtn=document.getElementById("buscarBtn")
const form=document.querySelector("form")
buscarBtn.addEventListener("click",async e=>{
    e.preventDefault()
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    const id=document.getElementById("id").value
    let p=document.getElementById("habitacion-info")
    try {
        let res=await fetch(`http://localhost:8080/habitaciones/buscar/${id}`)
        if (res.status===404){
            p.textContent="La habitacion no se encontro"
            p.style.color="red"
        }
        if (!res.ok){
            throw new Error("Error al buscar la habitacion")
        }
        let json=await  res.json()
        p.innerHTML=`Tipo: ${json.tipoHabitacion} <br> Capacidad: ${json.capacidad}`
        p.style.color="white"
    }catch (e){
        console.error(e)
    }
})