document.getElementById("buscarBtn").addEventListener("click",async e=>{
    e.preventDefault()

    const id=document.getElementById("id").value
    let info=document.getElementById("info-articulo")
    try{
        let res=await fetch(`http://localhost:8080/articulos/buscar/${id}`)
        if (res.status===404){
            info.textContent="El id ingresado no esta registrado"
            info.style.color="red"
            return
        }
        if (!res.ok){
            throw new Error("Error al buscar el articulo")
        }
        let articulo=await res.json()

        info.innerHTML=`
        Nombre: ${articulo.nomArticulo}<br>
        Cantidad: ${articulo.cantidad}<br>
        Descripcion: ${articulo.descripcion}
        `
        info.style.color="white"
    }catch (e){
        console.error(e)
    }
})