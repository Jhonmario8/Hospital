document.getElementById("guardarBtn").addEventListener("click",async e=>{
    e.preventDefault()

    const nombre=document.getElementById("nombre").value
    const precio=document.getElementById("precio").value
    const detalles=document.getElementById("detalles").value

    try{
        let response=await fetch("http://localhost:8080/servicios/mostrar")
        if (!response.ok){
            throw new Error("Error al obtener los servicio")
        }
        let servicios=await response.json()
        let servicio=servicios.find(s=>s.nomServicio.toLowerCase()===nombre.toLowerCase())
        if (servicio){
            if (confirm("Ya hay un servicio con ese nombre desea actualizarlo?")){
                let respuesta=await fetch("http://localhost:8080/servicios/guardar",{
                    method:"POST",
                    headers:{"Content-Type":"application/json"},
                    body:JSON.stringify({
                        codServicio: servicio.codServicio,
                        nomServicio: nombre,
                        precioServicio: precio,
                        detallesServicio: detalles
                    })
                })
                if (!respuesta.ok){
                    throw new Error("Error al actualizar el servicio")
                }
                alert("Servicio actualizado")
                document.querySelector("form").reset()
                return
            }else{
                alert("Operacion cancelada")
                return
            }
        }
        let res=await fetch("http://localhost:8080/servicios/guardar",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify({
                nomServicio: nombre,
                precioServicio: precio,
                detallesServicio: detalles
            })
        })
        let ult=await fetch("http://localhost:8080/servicios/mostrar")
        if (!ult.ok){
            throw new Error("Error al obtener los servicios")
        }
        let sers=await ult.json()
        let id=sers.at(sers.length-1).codServicio
        alert(`Servicio guardado con exito, ID asignado: ${id}`)
        document.querySelector("form").reset()
    }catch(e){
        console.error(e)
    }
})