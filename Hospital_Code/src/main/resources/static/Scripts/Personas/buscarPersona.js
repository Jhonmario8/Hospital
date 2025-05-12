let tabla=document.getElementById("personas-tabla")
function crearFila(per){
    let row=document.createElement("tr")
    row.innerHTML=`<td>${per.idPersona}</td>
            <td>${per.nomPersona}</td>
            <td>${per.edadPersona}</td>
            <td>${per.direccion}</td>
            <td>${per.telefonoPersona}</td>
            <td>${per.tipoPersona?"Empleado":"Paciente"}</td>
            `
    return row
}
async function mostrar(){
    let response=await fetch("http://localhost:8080/personas/mostrar")
    if (!response.ok){
        throw new Error("Error al obtener las personas")
    }
    let personas=await response.json()
    console.log(personas);
    
    tabla.innerHTML=""
    personas.slice(0,10).forEach(per => {
        let row=crearFila(per)
        tabla.appendChild(row)
    });
}

document.addEventListener("DOMContentLoaded",async e=>{
    try{mostrar()

    }catch(e){
        console.error(e)
    }
})

document.getElementById("id").addEventListener("input",async e=>{
    e.preventDefault()

    const id=e.target.value
    const info= document.getElementById("info")
    tabla.innerHTML=""
    try {

        if (id===""){
            mostrar()
            return
        }
        let res = await fetch(`http://localhost:8080/personas/buscar/${id}`)
        if (res.status===404){
            info.textContent="La persona no se encontro"
            info.style.color="red"
            return
        }
        if (!res.ok) {
            throw new Error("Error al obtener la persona")
        }
        let per=await res.json()
        let row=crearFila(per)
        tabla.appendChild(row)
        info.textContent=""

    }catch (e){
        console.error(e)
    }
})