document.addEventListener("DOMContentLoaded",async ()=>{
    try{
        let res=await fetch("http://localhost:8080/ingresos/mostrar")
        if (!res.ok){
            throw new Error("Error al obtener los pacientes")
        }
        let json=await res.json()
        let tabla=document.getElementById("tabla-pacientes")
        console.log(json)
        for (let pac of json) {
            let res=await fetch(`http://localhost:8080/personas/buscar/${pac.idPersona}`)
            if (!res.ok){
                throw new Error("Error al buscar el paciente")
            }
            let per=await res.json()
            let row=document.createElement("tr")
            row.innerHTML=`
            <td>${per.idPersona}</td>
            <td>${per.nomPersona}</td>
            <td>${pac.idHabitacion}</td>
            <td>${pac.acompañante?"Si":"No"}</td>
            `
            tabla.appendChild(row)
        }
    }catch (e){
        console.error(e)
    }
})