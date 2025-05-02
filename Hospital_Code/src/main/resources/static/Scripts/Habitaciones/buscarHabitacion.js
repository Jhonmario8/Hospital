const buscarBtn=document.getElementById("buscarBtn")
let tabla=document.getElementById("habitaciones-tabla")
function crearFila(hab){
    let row=document.createElement("tr")
    row.innerHTML=`
    <td>${hab.numHabitacion}</td>
    <td>${hab.tipoHabitacion}</td>
    <td>${hab.capacidad}</td>
    `
    return row
}
async function mostrar(){
    try{
        let res=await fetch("http://localhost:8080/habitaciones/mostrar")
        if (!res.ok){
            throw new Error("Error al obtener las habitaciones")
        }
        let habitaciones=await res.json()
        habitaciones.slice(0,10).forEach(hab=> {
            let row=crearFila(hab)
            tabla.appendChild(row)
        })
    }catch (e){
        console.error(e)
    }
}
document.addEventListener("DOMContentLoaded",mostrar)
document.getElementById("id").addEventListener("input",async e=>{
    e.preventDefault()

    const id=e.target.value
    tabla.innerHTML=""
    let p=document.getElementById("habitacion-info")
    try {
        if (id===""){
            mostrar()
        }
        let res=await fetch(`http://localhost:8080/habitaciones/buscar/${id}`)
        if (res.status===404){
            p.textContent="La habitacion no se encontro"
            p.style.color="red"
            return
        }
        if (!res.ok){
            throw new Error("Error al buscar la habitacion")
        }
        let json=await  res.json()

        let row=crearFila(json)
        tabla.appendChild(row)
        p.textContent=""
    }catch (e){
        console.error(e)
    }
})