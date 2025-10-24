let imagenes=[
"https://media.istockphoto.com/id/1298375809/es/foto/habitaci%C3%B3n-de-hospital-moderno-de-lujo-vac%C3%ADa.jpg?s=612x612&w=0&k=20&c=w1J8FFPOH1YIuRwvqvrS04yaMP1CTFSu5jIVICRmxYE=",
"https://hic.fcv.org/co/images/hic/unidad-de-hospitalizacion/unidad-de-hospitalizacion-vip-2.jpg",
"https://hospitecnia.com/sites/default/files/158828572911588285729.jpg",
"https://i.pinimg.com/736x/5e/46/84/5e4684b3fc31b241b6bc84c1b61f8057.jpg",
"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTa1v_gOpOFdaWAtAibzeGT1tzu-83TC-MRIw&s",
"https://a1.elespanol.com/cronicaglobal/2015/01/11/vida/vida_2509951_553336_1024x576.jpg",
"https://media.istockphoto.com/id/1090482448/es/foto/habitaci%C3%B3n-de-hospital-con-camas-y-c%C3%B3moda-m%C3%A9dica-equipada.jpg?s=612x612&w=0&k=20&c=v-0PMkQpIchIcDqQUAs8C9vrImPHnwYLkEoYHyu4Mlg=",
]

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
            if (imagenes.length<=i){
                i=0
            }
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