let tabla=document.getElementById("personas-tabla")
const form=document.querySelector("form")
function crearInput(id, labelText, value) {
    const label = document.createElement("label");
    label.setAttribute("for", id);
    label.textContent = labelText;

    const input = document.createElement("input");
    input.setAttribute("id", id);
    input.setAttribute("required", true);
    input.value = value;

    return [label, input];
}
function crearFila(per){
    let row=document.createElement("tr")
    row.innerHTML=`<td>${per.idPersona}</td>
            <td>${per.nomPersona}</td>
            <td>${per.edadPersona}</td>
            <td>${per.telefonoPersona}</td>
            <td>${per.direccion}</td>
            <td>${per.tipoPersona?"Medico":"Paciente"}</td>
            <td class="td"><button class="editar">Editar</button> <button class="borrar">Inactivar</button></td>
            `
    row.querySelector(".editar").addEventListener("click",async e=>{
            e.preventDefault()
        const id=per.idPersona
        document.querySelector("table").innerHTML=""
        try {
            let response=await fetch(`http://localhost:8080/personas/buscar/${id}`)
            if (response.status===404){
                alert("El id ingresado no esta registrado")
                return
            }
            if(!response.ok){
                throw new Error("Error al buscar la persona")
            }
            let persona=await response.json()
            form.style.width="500px"
            form.innerHTML=""
            let nombre=crearInput("nombre","Nombre: ",persona.nomPersona)
            let edad=crearInput("edad","Edad: ",persona.edadPersona)
            let direccion= crearInput("direccion","Direccion: ",persona.direccion)
            let telefono=crearInput("telefono","Telefono: ",persona.telefonoPersona)
            let message=document.createElement("p")

            const labelTipo=document.createElement("label")
            labelTipo.setAttribute("for","tipo")
            labelTipo.textContent="Tipo: "
            const tipo = document.createElement("select");
            tipo.setAttribute("id", "tipo");
            tipo.setAttribute("class","styled-select")

            const optionEmpleado = document.createElement("option");
            optionEmpleado.value = "true";
            optionEmpleado.textContent = "Medico";

            const optionPaciente = document.createElement("option");
            optionPaciente.value = "false";
            optionPaciente.textContent = "Paciente";

            tipo.appendChild(optionEmpleado);
            tipo.appendChild(optionPaciente);

            const actualizarBtn=document.createElement("button")
            actualizarBtn.textContent="Actualizar"

            const volverBtn=document.createElement("a")
            volverBtn.setAttribute("href","../../html/GestionPersonas/gestionpersona.html")
            volverBtn.setAttribute("class","button")
            volverBtn.textContent="Volver"
            telefono.at(1).addEventListener("input",e=>{
            if (e.target.value.length>10){
                e.target.value=e.target.value.slice(0,10)
            }
            })
            actualizarBtn.addEventListener("click",async e=>{
                e.preventDefault()
                if(telefono.at(1).value.length<10 ){
                    telefono.at(1).setCustomValidity("Ingrese un numero de 10 digitos")
                    form.reportValidity()
                    return
                }else {
                    telefono.at(1).setCustomValidity("")
                }
                if (!form.checkValidity()){
                    form.reportValidity();
                    return
                }

                try {

                    let res=await fetch("http://localhost:8080/personas/actualizar",{
                        method:"PUT",
                        headers:{"Content-Type":"application/json"},
                        body:JSON.stringify({
                            idPersona: id,
                            nomPersona: nombre.at(1).value,
                            edadPersona: edad.at(1).value,
                            direccion: direccion.at(1).value,
                            telefonoPersona: telefono.at(1).value,
                            tipoPersona: tipo.value
                        })
                    })
                    if (!res.ok){
                        throw new Error("Error al actualizar la persona")
                    }
                    alert("Persona actualizada con exito")
                    window.location.reload()
                }catch (e){
                    console.error(e)
                }
            })
            edad.at(1).min="0"
            edad.at(1).max="110"
            edad.at(1).type="number"
            telefono.at(1).type="number"
            direccion.at(1).maxLength="30"
            nombre.at(1).maxLength="20"
            form.appendChild(nombre.at(0))
            form.appendChild(nombre.at(1))
            form.appendChild(edad.at(0))
            form.appendChild(edad.at(1))
            form.appendChild(direccion.at(0))
            form.appendChild(direccion.at(1))
            form.appendChild(telefono.at(0))
            form.appendChild(telefono.at(1))
            form.appendChild(message)
            form.appendChild(labelTipo)
            form.appendChild(tipo)
            form.appendChild(actualizarBtn)
            form.appendChild(volverBtn)
        }catch (e){
            console.error(e)
        }
    })
    row.querySelector(".borrar").addEventListener("click",async e=>{
        e.preventDefault()

        const id=per.idPersona
        try {
            let response= await fetch(`http://localhost:8080/personas/buscar/${id}`)
            if (response.status===404){
                alert("El id ingresado no esta registrado")
                return
            }
            if (!response.ok){
                throw  new Error("Error al buscar la persona")
            }
            let conf=confirm("Estas seguro que deseas inactivar la persona?")
            if (conf) {
                let res = await fetch(`http://localhost:8080/personas/borrar/${id}`,{
                    method:"DELETE"
                })
                if (!res.ok){
                    throw new Error("Error al inactivar la persona")
                }
                alert("Persona inactivada correctamente")
                window.location.reload()
            }
        }catch (e){
            console.error(e)
        }
    })
    return row
}

async function mostrar(){
    let response=await fetch("http://localhost:8080/personas/mostrar")
    if (!response.ok){
        throw new Error("Error al obtener las personas")
    }
    let personas=await response.json()
    
    tabla.innerHTML=""
    personas.slice(0,10).forEach(per => {
        let row=crearFila(per)
        tabla.appendChild(row)
    });
}

document.addEventListener("DOMContentLoaded",async e=>{
    try{mostrar()

    }catch(e){
        console.error(e)
    }
})

document.getElementById("id").addEventListener("input",async e=>{
    e.preventDefault()

    const id=e.target.value
    const info= document.getElementById("info")
    tabla.innerHTML=""
    try {

        if (id===""){
            mostrar()
            return
        }
        let res = await fetch(`http://localhost:8080/personas/buscar/${id}`)
        if (res.status===404){
            info.textContent="La persona no se encontro"
            info.style.color="red"
            return
        }
        if (!res.ok) {
            throw new Error("Error al obtener la persona")
        }
        let per=await res.json()
        let row=crearFila(per)
        tabla.appendChild(row)
        info.textContent=""

    }catch (e){
        console.error(e)
    }
})