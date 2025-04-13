document.addEventListener("DOMContentLoaded",async ()=>{
    try{
        let res=await fetch("http://localhost:8080/ingresos/mostrar")
        if (!res.ok){
            throw new Error("Error al obtener los pacientes")
        }
        let json=await res.json()
        let tabla=document.getElementById("tabla-pacientes")
        json.forEach(pac=>{
            let row=document.createElement("tr")
            row.innerHTML=`
            <td>${pac.idIngreso}</td>
            <td>${pac.persona.nomPersona}</td>
            <td>${pac.ciudad}</td>
            <td>${pac.motivo}</td>
            <td>${pac.acompañante?"Si":"No"}</td>
            `
            tabla.appendChild(row)
        })
    }catch (e){
        console.error(e)
    }
})