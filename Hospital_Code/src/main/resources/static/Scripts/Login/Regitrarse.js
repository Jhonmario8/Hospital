const registrarseBtn=document.getElementById("registrarseBtn")

    registrarseBtn.addEventListener("click", async e => {
        if (!form.checkValidity()) {
            return;
        }

        e.preventDefault()
        const usuario = document.getElementById("user").value
        const contraseña = document.getElementById("contraseña").value
        try {
            let response=await fetch(`http://localhost:8080/usuario/search/${usuario}`)
            if(response.ok){
                alert("El usuario ya existe")
                return
            }
            let res = await fetch("http://localhost:8080/usuario/guardar", {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({usuario: usuario, contraseña: contraseña})
            })
            if (!res.ok) {
                throw new Error("Error al guardar el usuario")
            }
            let data = res.text()
            alert("Usuario Creado Con Exito", data)
            window.location.href = "../../html/InicioSesion/InicioSesion.html"
        } catch (e) {
            console.error(e)
        }
    })

