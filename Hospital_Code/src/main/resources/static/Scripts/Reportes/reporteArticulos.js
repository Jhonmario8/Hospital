document.addEventListener("DOMContentLoaded",async ()=>{

    try{
        let res=await fetch("http://localhost:8080/articulos/mostrar")
        if (!res.ok){
            throw new Error("Error al obtenero los articulos")
        }
        let json=await res.json()
        let tabla=document.getElementById("tabla-articulos")
        json.forEach(art=>{
            let row=document.createElement("tr")
            row.innerHTML=`
            <td>${art.idArticulo}</td>
            <td>${art.nomArticulo}</td>
            <td>${art.cantidad}</td>
            <td>${art.descripcion}</td>
            `
            if (art.habitaciones != null && art.habitaciones.length > 0) {
                let cad = "";
                let i = 0;
                for (hab of art.habitaciones) {
                    if (i === art.habitaciones.length - 1) {
                        cad += "ID: " + hab.numHabitacion;
                        continue;
                    }
                    cad += "ID: " + hab.numHabitacion + " , ";
                    i++;
                }
                row.innerHTML += `<td>${cad}</td>`;
            } else {
                row.innerHTML += `<td>No asignado</td>`;
            }

            tabla.appendChild(row)
        })
    }catch (e){
        console.error(e)
    }
})