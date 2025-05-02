let tabla=document.getElementById("servicios-tabla")
function crearFila(ser){
    let row=document.createElement("tr")
    row.innerHTML=`
            <td>${ser.codServicio}</td>
            <td>${ser.nomServicio}</td>
            <td>${ser.precioServicio}</td>
            <td>${ser.detallesServicio}</td>
            `
    return row
}
async function mostrar(){
    try{
        let res=await fetch("http://localhost:8080/servicios/mostrar")
        if (!res.ok){
            throw new Error("Error al obtener los servicios")
        }
        let servicios=await res.json()
        servicios.slice(0,10).forEach(ser=>{
            let row=crearFila(ser)
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
    let p=document.getElementById("info-servicio")
    try{
        if (id===""){
            mostrar()
        }
        let res=await fetch(`http://localhost:8080/servicios/buscar/${id}`)
        if (res.status===404){
            p.textContent="El servicio no se encontro"
            p.style.color="red"
            return
        }
        if (!res.ok){
            throw new Error("Error al buscar el servicio")
        }
        let servicio=await res.json()
        let row=crearFila(servicio)
        tabla.appendChild(row)
        p.textContent=""
    }  catch (e){
        console.error(e)
    }
})