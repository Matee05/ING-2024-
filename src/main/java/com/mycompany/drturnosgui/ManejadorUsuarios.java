package com.mycompany.drturnosgui;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ManejadorUsuarios {
    private Set<Usuario> usuarios;

    public ManejadorUsuarios() {
        this.usuarios = cargarUsuarios();
    }

    public Usuario verificarCredenciales(String username, String password) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                return usuario; // Retorna el usuario si las credenciales son correctas
            }
        }
        return null; // Retorna null si las credenciales son incorrectas
    }

    // Método para agregar un nuevo usuario, ahora incluyendo el rol
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        guardarUsuarios();
    }

    // Cargar usuarios desde un archivo
    private Set<Usuario> cargarUsuarios() {
        try {
            FileInputStream fileInputStream = new FileInputStream("usuarios.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Set<Usuario> usuariosDeserializados = (Set<Usuario>) objectInputStream.readObject();
            objectInputStream.close();
            return usuariosDeserializados;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashSet<>(); // Retornar un conjunto vacío si hay un error
        }
    }

    // Guardar usuarios en un archivo
    private void guardarUsuarios() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("usuarios.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(usuarios);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean autenticarUsuario(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
