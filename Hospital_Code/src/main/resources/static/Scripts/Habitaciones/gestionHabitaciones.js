const buscarBtn=document.getElementById("buscarBtn")
let tabla=document.getElementById("habitaciones-tabla")
const form = document.querySelector("form")
function crearFila(hab){
    let row=document.createElement("tr")
    row.innerHTML=`
    <td>${hab.numHabitacion}</td>
    <td>${hab.tipoHabitacion}</td>
    <td>${hab.capacidad}</td>
    <td class="td"><button class="editar">Editar</button> <button class="borrar">Borrar</button></td>
    `
    row.querySelector(".editar").addEventListener("click",async e=>{
        e.preventDefault()
        const id=hab.numHabitacion
        document.querySelector("table").innerHTML=""
        try{
            let response= await fetch(`http://localhost:8080/habitaciones/buscar/${id}`)
            if (response.status===404){
                alert("El id ingresado no esta registrado")
                return
            }
            if (!response.ok){
                throw new Error("Error al buscar la habitacion")
            }
            let json=await response.json()
            form.innerHTML=""
            let [labelT,tipo]=crearInput("tipo","Tipo: ",json.tipoHabitacion)
            let [labelC,capacidad]=crearInput("capacidad","Capacidad: ",json.capacidad)

            const actualizarBtn=document.createElement("button")
            actualizarBtn.textContent="Actualizar"

            const volverBtn=document.createElement("a")
            volverBtn.textContent="Volver"
            volverBtn.setAttribute("class","button")
            volverBtn.setAttribute("href","../../html/GestionHabitaciones/gestionhabitaciones.html")
            actualizarBtn.addEventListener("click",async e=>{
                e.preventDefault()
                try {
                    let res=await fetch(`http://localhost:8080/habitaciones/actualizar`,{
                        method:"PUT",
                        headers:{"Content-Type":"application/json"},
                        body:JSON.stringify({
                            numHabitacion: id,
                            tipoHabitacion: tipo.value,
                            capacidad: capacidad.value
                        })
                    })
                    if (!res.ok){
                        throw new Error("Error al actualizar la habitacion")
                    }
                    alert("Habitacion actualizada correctamente")
                    window.location.reload()
                }catch (e){
                    console.error(e)
                }
            })

            form.appendChild(labelT)
            form.appendChild(tipo)
            form.appendChild(labelC)
            form.appendChild(capacidad)
            form.appendChild(actualizarBtn)
            form.appendChild(volverBtn)

        }catch (e){
            console.error(e)
        }
    })
    row.querySelector(".borrar").addEventListener("click",async e=>{
        e.preventDefault()
        const id=hab.numHabitacion
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
                window.location.reload()
            }
        }catch (e){
            console.error(e)
        }
    })
    return row
}
function crearInput(id,lblTxt,value){
    const label=document.createElement("label")
    label.setAttribute("for",id)
    label.textContent=lblTxt
    const input=document.createElement("input")
    input.setAttribute("id",id)
    input.setAttribute("required",true)
    input.value=value

    return [label,input]
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