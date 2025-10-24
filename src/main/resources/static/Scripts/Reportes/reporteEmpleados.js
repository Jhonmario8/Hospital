document.addEventListener("DOMContentLoaded",async ()=>{
    try{
        let res=await fetch("http://localhost:8080/personas/empleados")
        if (!res.ok){
            throw new Error("Error al obtener los empleados")
        }
        let json=await res.json()
        let tabla=document.getElementById("tabla-empleados")
        json.forEach(emp=>{
            let row=document.createElement("tr")
            row.innerHTML=`
            <td>${emp.idPersona}</td>
            <td>${emp.nomPersona}</td>
            <td>${emp.edadPersona}</td>
            <td>${emp.telefonoPersona}</td>
            `
            tabla.appendChild(row)
        })

    }catch (e){
        console.error(e)
    }
})