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

document.getElementById("buscarBtn").addEventListener("click",async e=>{
    e.preventDefault()

    const id=document.getElementById("id").value

    try{
        let response=await fetch(`http://localhost:8080/articulos/buscar/${id}`)
        if (response.status===404){
            alert("El id ingresado no esta registrado")
            return
        }
        if (!response.ok){
            throw new Error("Error al buscar el articulo")
        }
        let articulo=await response.json()
        let form=document.getElementById("form")
        form.innerHTML=""
        let [labelN,nombre]=crearInput("nombre","Nombre: ",articulo.nomArticulo)
        let [labelC,cantidad]=crearInput("cantidad","Cantidad: ",articulo.cantidad)
        let [labelD,descripcion]=crearInput("descripcion","Descripcion: ",articulo.descripcion)

        const actualizarBtn=document.createElement("button")
        actualizarBtn.textContent="Actualizar"
        const volverBtn=document.createElement("a")
        volverBtn.textContent="Volver"
        volverBtn.setAttribute("href","../../html/GestionArticulos/actualizaarticulo.html")
        volverBtn.setAttribute("class","button")

        actualizarBtn.addEventListener("click",async e=>{
            e.preventDefault()
            try{
                let res=await fetch("http://localhost:8080/articulos/actualizar",{
                    method:"POST",
                    headers:{"Content-Type":"application/json"},
                    body:JSON.stringify({
                        idArticulo: id,
                        nomArticulo: nombre.value,
                        cantidad: cantidad.value,
                        descripcion: descripcion.value
                    })
                })
                if (!res.ok){
                    throw new Error("Error al actualizar el articulo")
                }
                alert("Articulo actualizado correctamente")
                window.location.href="../../html/GestionArticulos/actualizaarticulo.html"
            }catch (e){
                console.error(e)
            }
        })

        form.appendChild(labelN)
        form.appendChild(nombre)
        form.appendChild(labelC)
        form.appendChild(cantidad)
        form.appendChild(labelD)
        form.appendChild(descripcion)
        form.appendChild(actualizarBtn)
        form.appendChild(volverBtn)
    }catch (e){
        console.error(e)
    }
})