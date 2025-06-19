// Call the dataTables jQuery plugin
$(document).ready(function () {
    // on reaady
});

async function iniciarSesion() {
    let email = document.getElementById("txtEmail").value;
    let password = document.getElementById("txtPassword").value;


    let datos = {}
    datos.email = email;
    datos.password = password;

    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)

    });
    const respuesta = await request.text();
    if(respuesta == 'OK'){
     window.location.href = "usuarios.html"   
    }else{
        alert("Credenciales incorrectas")
    }
};


