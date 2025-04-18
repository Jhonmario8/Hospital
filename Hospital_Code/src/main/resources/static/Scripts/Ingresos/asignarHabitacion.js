let imagenes=[
"https://media.istockphoto.com/id/1298375809/es/foto/habitaci%C3%B3n-de-hospital-moderno-de-lujo-vac%C3%ADa.jpg?s=612x612&w=0&k=20&c=w1J8FFPOH1YIuRwvqvrS04yaMP1CTFSu5jIVICRmxYE=",
"https://hic.fcv.org/co/images/hic/unidad-de-hospitalizacion/unidad-de-hospitalizacion-vip-2.jpg",
"https://st2.depositphotos.com/1518767/46787/i/1600/depositphotos_467875854-stock-photo-general-view-empty-hospital-room.jpg",
"https://a1.elespanol.com/cronicaglobal/2015/01/11/vida/vida_2509951_553336_1024x576.jpg",
"https://media.istockphoto.com/id/1090482448/es/foto/habitaci%C3%B3n-de-hospital-con-camas-y-c%C3%B3moda-m%C3%A9dica-equipada.jpg?s=612x612&w=0&k=20&c=v-0PMkQpIchIcDqQUAs8C9vrImPHnwYLkEoYHyu4Mlg="]

let i=0

document.addEventListener("DOMContentLoaded",async ()=>{
    let idIngreso= localStorage.getItem("idIngreso")
    localStorage.removeItem("idIngreso")
    let fragment=document.createDocumentFragment()
    let galeria=document.getElementById("galeria")
    try{
        let response=await fetch("http://localhost:8080/habitaciones/mostrar")
        if (!response.ok){
            throw new Error("Error al obtener las habitaciones")
        }

        let habitaciones=await response.json()
        habitaciones.forEach(hab=>{
            let div=document.createElement("div")
            div.setAttribute("class","gallery-item")
            div.setAttribute("data-id",hab.numHabitacion)
            div.innerHTML=`<button type="submit" value="${hab.numHabitacion}" class="image-button">
       <img src="${imagenes[i]}" alt="Imagen de la Habitación">
   </button>
   <div class="info">
       <h3>Número de Habitación</h3>
       <h3>${hab.numHabitacion}</h3>
   </div>
`
            i++
            fragment.appendChild(div)
        })
        galeria.appendChild(fragment)
        document.querySelectorAll(".gallery-item").forEach(item=>{
            item.addEventListener("click",async e=>{
                e.preventDefault()
                let idHab=item.getAttribute("data-id");
                try{
                    let res=await fetch(`http://localhost:8080/ingresos/asignar/${idIngreso}/habitacion/${idHab}`,{
                        method:"POST"
                    })
                    if (!res.ok){
                        throw new Error("Error al asignar la habitacion")
                    }
                    alert("Habitacion asignada con exito")
                    window.location.href="../../html/GestionIngresos/gestioningresos.html"
                }catch (e) {
                    console.error(e)
                }
            })
        })
    }catch (e){
        console.error(e)
    }
})