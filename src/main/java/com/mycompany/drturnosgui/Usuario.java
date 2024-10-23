package com.mycompany.drturnosgui;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1562261206917216737L; // Asegúrate de actualizar este número si realizas cambios significativos

    private String username;
    private String password;
    private UsuarioRol rol; // Añadido para el rol de usuario

    // Constructor que acepta username, password y rol
    public Usuario(String username, String password, UsuarioRol rol) {
        this.username = username;
        this.password = password;
        this.rol = rol; // Asignar el rol
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UsuarioRol getRol() {
        return rol; // Método para obtener el rol
    }
}
