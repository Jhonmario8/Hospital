document.addEventListener("DOMContentLoaded",async ()=>{
    try{
        let res=await fetch("http://localhost:8080/habitaciones/mostrar")
        if (!res.ok){
            throw new Error("Error al obtener la habitaciones")
        }
        let json=await res.json()
        let tabla = document.getElementById("tabla-habitaciones")
        json.forEach(hab=>{
            let row=document.createElement("tr")
            row.innerHTML=`
            <td>${hab.numHabitacion}</td>
            <td>${hab.tipoHabitacion}</td>
            <td>${hab.capacidad}</td>
            `
            tabla.appendChild(row)
        })
    }catch (e){
        console.error(e)
    }
})