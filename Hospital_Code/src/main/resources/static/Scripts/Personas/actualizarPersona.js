const buscarBtn=document.getElementById("buscarBtn")

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
const form=document.querySelector("form")
buscarBtn.addEventListener("click",async e=>{
    e.preventDefault()
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    const id=document.getElementById("id").value

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
        form.innerHTML=""

        let nombre=crearInput("nombre","Nombre: ",persona.nomPersona)
        let edad=crearInput("edad","Edad: ",persona.edadPersona)
        let direccion= crearInput("direccion","Direccion: ",persona.direccion)
        let telefono=crearInput("telefono","Telefono: ",persona.telefonoPersona)

        const labelTipo=document.createElement("label")
        labelTipo.setAttribute("for","tipo")
        labelTipo.textContent="Tipo: "
        const tipo = document.createElement("select");
        tipo.setAttribute("id", "tipo");

        const optionEmpleado = document.createElement("option");
        optionEmpleado.value = "true";
        optionEmpleado.textContent = "Empleado";

        const optionPaciente = document.createElement("option");
        optionPaciente.value = "false";
        optionPaciente.textContent = "Paciente";

        tipo.appendChild(optionEmpleado);
        tipo.appendChild(optionPaciente);

        const actualizarBtn=document.createElement("button")
        actualizarBtn.textContent="Actualizar"

        const volverBtn=document.createElement("a")
        volverBtn.setAttribute("href","../../html/GestionPersonas/actualizapersona.html")
        volverBtn.setAttribute("class","button")
        volverBtn.textContent="Volver"
        
        actualizarBtn.addEventListener("click",async e=>{
            e.preventDefault()

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
                    throw new Error("Error al actualizar la tarea")
                }
                alert("Persona actualizada con exito")
                window.location.href="../../html/GestionPersonas/actualizapersona.html"
            }catch (e){
                console.error(e)
            }
        })
        form.appendChild(nombre.at(0))
        form.appendChild(nombre.at(1))
        form.appendChild(edad.at(0))
        form.appendChild(edad.at(1))
        form.appendChild(direccion.at(0))
        form.appendChild(direccion.at(1))
        form.appendChild(telefono.at(0))
        form.appendChild(telefono.at(1))
        form.appendChild(labelTipo)
        form.appendChild(tipo)
        form.appendChild(actualizarBtn)
        form.appendChild(volverBtn)
    }catch (e){
        console.error(e)
    }
})