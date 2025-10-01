const iniciarBtn=document.getElementById("iniciarBtn")
const form=document.querySelector("form")

iniciarBtn.addEventListener("click", async e => {
    e.preventDefault()
    if (!form.checkValidity()) {
        form.reportValidity();
        return;
    }
    const usuario = document.getElementById("user").value
    const contraseña = document.getElementById("contraseña").value
    try {
        let res = await fetch(`http://localhost:8080/usuario/buscar`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({usuario: usuario, contraseña: contraseña})
        })
        if (res.status === 404) {
            alert("Usuario o contraseña incorrectos")
        }
        if (!res.ok) {
            throw new Error("Error al obtener el usuario")
        }
        let json = await res.json()
        console.log("Usuario autenticado:", json);
        window.location.href = "../../html/index.html"
    } catch (e) {
        console.error(e)
    }
})
