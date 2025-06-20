// Call the dataTables jQuery plugin
$(document).ready(function () {
    // on reaady
});

async function registrarUsuario() {
    let nombre = document.getElementById("txtNombre").value;
    let apellido = document.getElementById("txtApellido").value;
    let email = document.getElementById("txtEmail").value;
    let telefono = document.getElementById("txtTelefono").value;
    let password = document.getElementById("txtPassword").value;
    let repetirPassword = document.getElementById("repetirPassword").value;


    let datos = {}
    datos.nombre = nombre;
    datos.apellido = apellido;
    datos.email = email;
    datos.telefono = telefono;
    datos.password = password;

    if(repetirPassword != password){
        alert("Las contraseñas no coinciden");
        return;
    }

    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)

    });
    alert("La cuenta fué creada con éxitos")
    window.location.href= "login.html"
    //const usuarios = await request.json();
   
};


