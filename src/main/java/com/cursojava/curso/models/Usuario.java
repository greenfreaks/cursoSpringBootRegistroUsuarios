package com.cursojava.curso.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity // Hace referencia a la DB
@Table(name = "usuarios") // Hace referencia al nombre de la tabla de la DB
@ToString @EqualsAndHashCode // Estas 2 anotaciones pertenecen a LOMBOK y traen consigo las funciones TpString y toEqualsAndHashCde
public class Usuario {
    @Getter @Setter  // Esto es una función de LOMBOK para estar evitando crear funciones getter y setter por cada parámetro
    @Id // Indicamos que este parámetro va a ser la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generamos el ID automáticamente
    @Column(name = "id") // Este es el nombre de la columna de la tabla
    private Long id;
    @Getter @Setter @Column(name = "nombre")
    private String nombre;
    @Getter @Setter @Column(name = "apellido")
    private String apellido;
    @Getter @Setter @Column(name = "email")
    private String email;
    @Getter @Setter @Column(name = "telefono")
    private String telefono;
    @Getter @Setter @Column(name = "password")
    private String password;
}
