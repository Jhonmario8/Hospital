document.getElementById("buscarBtn").addEventListener("click",async e=>{
  e.preventDefault()

  const id=document.getElementById("id").value
    let p=document.getElementById("info-servicio")
  try{
      let res=await fetch(`http://localhost:8080/servicios/buscar/${id}`)
      if (res.status===404){
          p.textContent="El servicio no se encontro"
          p.style.color="red"
      }
      if (!res.ok){
          throw new Error("Error al buscar el servicio")
      }
      let servicio=await res.json()
      p.innerHTML=`Nombre: ${servicio.nomServicio}<br>
            Precio: ${servicio.precioServicio}<br>
            Detalles: ${servicio.detallesServicio}
`
      p.style.color="white"
  }  catch (e){
      console.error(e)
  }
})