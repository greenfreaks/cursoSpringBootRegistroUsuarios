package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Hace referencia la conexión con la DB
@Transactional // Le da la funcionalidad a esta clase de poder armar las consultas a la DB
public class UsuarioDaoImp implements UsuarioDao {

    @PersistenceContext
    private EntityManager entityManager; // Sirve para hacer la conexion con la DB

    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario"; // "Usuario" no se refiere al nombre de la tabla, si no al nombre de la clase (modelo)"
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Usuario getOneUser() {
        return null;
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class ,id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public boolean verificarCredenciales(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email"; // "Usuario" no se refiere al nombre de la tabla, si no al nombre de la clase (modelo)"
        List <Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        if(lista.isEmpty()){
            return false;
        }

        String passwordHashed = lista.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        return argon2.verify(passwordHashed, usuario.getPassword()); // El primer parámetro compara la contraseña que está en la DB, mientras que el segundo compara la que le estamos pasando, esto devuelve un booleano

    } // Esta query que retorna un valor de tipo Lista Usuario que devolverá un usuario que haya cumplido con estas credenciales, va a devolver una fila (TRUE), si no, no (FALSE)

}
