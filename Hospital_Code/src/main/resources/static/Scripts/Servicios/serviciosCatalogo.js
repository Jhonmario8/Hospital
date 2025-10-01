addEventListener("DOMContentLoaded",async e=>{
    try{
        let response=await fetch("http://localhost:8080/servicios/mostrar")
        if (!response.ok){
            throw new Error("Error al obtener los servicios")
        }
        let servicios=await response.json()
        let tabla=document.getElementById("tabla-servicios")
        servicios.forEach(ser=>{
            let row=document.createElement("tr")
            row.innerHTML=`
            <td>${ser.codServicio}</td>
            <td>${ser.nomServicio}</td>
            <td>${ser.precioServicio}</td>
            <td>${ser.detallesServicio}</td>
            <td><a class="button adquirirBtn"  data-id="${ser.codServicio}">adquirir</a></td>
            `
            tabla.appendChild(row)
        })
        document.querySelectorAll(".adquirirBtn").forEach(btn=>{
            btn.addEventListener("click",e=>{
                e.preventDefault()
                let id=e.target.getAttribute("data-id")
                localStorage.setItem("idServicio",id)
                window.location.href="../../html/GestionServicios/adquirirservicio.html"
            })
        })
    }catch (e){
        console.error(e)
    }
})