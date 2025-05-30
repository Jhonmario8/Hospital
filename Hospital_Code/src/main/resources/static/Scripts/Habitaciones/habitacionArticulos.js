const tbody=document.getElementById("tbody")
const idHab=sessionStorage.getItem("idHab")
function crearFila(art){
    let row=document.createElement("tr")
    row.innerHTML=`
    <td>${art.idArticulo}</td>
    <td>${art.nomArticulo}</td>
    <td>${art.descripcion}</td>
    `
    return row
}

function mostrar(articulos){
    articulos.forEach(art=>{
    let row=crearFila(art)
        tbody.appendChild(row)
    })
}
document.addEventListener("DOMContentLoaded", async e=>{
    try{
        let res=await fetch(`http://localhost:8080/habitaciones/getAticulos/${idHab}`)
        if (!res.ok){
            throw new Error("Error al obtener los articulos")
        }
        let articulos=await res.json()
        if (articulos.length===0){
            let table= document.querySelector("table")
            let thead=table.querySelector("thead")
            let tr=thead.querySelector("tr")
            tr.innerHTML=`<th colspan="3">No hay articulos asignados</th>`
            return
        }
        mostrar(articulos)

        document.getElementById("id").addEventListener("input",async e=>{
            e.preventDefault()
            tbody.innerHTML=""
            let info=document.getElementById("info")
            info.textContent=""
            const id=e.target.value
            try{
                if (id===""){
                    mostrar(articulos)
                    return
                }
                let response=await fetch(`http://localhost:8080/articulos/buscar/${id}`)
                if (response.status===404){
                    info.style.color="red"
                    info.textContent="El articulo no se encontro"
                    return
                }
                if (!response.ok){
                    throw new Error("Error al buscar el articulo")
                }
                let art=await response.json()
                const existe = articulos.some(a => a.idArticulo === art.idArticulo)

                if (!existe) {
                    info.style.color = "red";
                    info.textContent = "El artículo no se encontró";
                    return;
                }

                let row=crearFila(art)
                tbody.appendChild(row)
            }catch (e){
                console.error(e)
            }
        })
    }catch (e){
        console.error(e)
    }
})

