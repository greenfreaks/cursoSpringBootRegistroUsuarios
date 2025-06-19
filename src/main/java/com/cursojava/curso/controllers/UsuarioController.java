/*
 * En este archivo vamos a crear el endpoint al cual accederemos a la info del usuario
 *
 */
package com.cursojava.curso.controllers;
import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController{

    @Autowired //
    private UsuarioDao usuarioDao;

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Mario");
        usuario.setApellido("Sandoval");
        usuario.setEmail("mariosandovalv1998@gmail.com");
        usuario.setTelefono("56198238733");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios")
    public List <Usuario> getUsuarios(){ // al lado de la palabra "public", estamos diciendo que esa unción va a retornar un listao de usuarios, es Java, todas las funciones deben de llevar el tipo de dato que van a retornar
        return usuarioDao.getUsuarios(); // Ya haciendo uso de la interface usuarioDao, evitamos estar hardcodeando usuarios en esta función

        /*
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Mario");
        usuario.setApellido("Sandoval");
        usuario.setEmail("mariosandovalv1998@gmail.com");
        usuario.setTelefono("56198238733");

        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNombre("Karen Marlene");
        usuario2.setApellido("Sandoval Velázquez");
        usuario2.setEmail("karen@gmail.com");
        usuario2.setTelefono("7731138781");

        usuarios.add(usuario);
        usuarios.add(usuario2);
        return usuarios;
         */
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){ // Aquí como la función no va a retornar nada, al lado de la palabra "public", escribimos "void"

        //En las siguientes 1 línwas estamos encriptando la contraseña mediante el uso de la librería ARGON2 que agregamos manualmente como dependencia en el POM, desconozco cómo funciona a fondo, pero puedo usar este ejemplo para futuros proyectos
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword()); // dentro de la varible HASH guardamos la contraseña encriptada, notemos que en el último parámetro estamos usando el método getPassword() creado en el modelo Usuario

        usuario.setPassword(hash); // Mediante el método setPassword creado en el Modelo Usuario, estamos asignandole el valor de la variable HASH

        //usuario.setTelefono("123456789"); de esta forma podríamos insertar
        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "api/usuario45")
    public Usuario editarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Mario");
        usuario.setApellido("Sandoval");
        usuario.setEmail("mariosandovalv1998@gmail.com");
        usuario.setTelefono("56198238733");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE) // Que el método sea del tipo DELETE, no significa que con esto ya vayamos a borrar un usuario, el método puede ser DELETE, pero lo que hagamos internamente con esta función puede ser cualquier cosa, lo único que está sucediendo es que si entran por esta URL y por el método DELETE, va a entrar esta función
    public void eliminar(@PathVariable Long id){ // es una función del tipo VOID, porque no va a devolver nada
        usuarioDao.eliminar(id);
    }

    @RequestMapping(value = "api/usuario123")
    public Usuario buscarUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Mario");
        usuario.setApellido("Sandoval");
        usuario.setEmail("mariosandovalv1998@gmail.com");
        usuario.setTelefono("56198238733");
        return usuario;
    }
}
