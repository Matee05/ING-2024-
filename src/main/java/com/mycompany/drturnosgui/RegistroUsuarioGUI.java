package com.mycompany.drturnosgui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class RegistroUsuarioGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private ManejadorUsuarios manejadorUsuarios;

    public RegistroUsuarioGUI(ManejadorUsuarios manejadorUsuarios) {
        this.manejadorUsuarios = manejadorUsuarios;
        setTitle("Crear Cuenta");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Usuario:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordField = new JPasswordField();

        JButton registerButton = new JButton("Registrar");
        registerButton.addActionListener(e -> handleRegister());

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Espacio vacío
        panel.add(registerButton);

        add(panel);
    }

    private void handleRegister() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Validación básica
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear nuevo usuario y agregarlo al manejador
        UsuarioRol rol = UsuarioRol.NORMAL; // Asignar un rol por defecto
        Usuario nuevoUsuario = new Usuario(username, password, rol);
        manejadorUsuarios.agregarUsuario(nuevoUsuario);

        // Mensaje de éxito y cerrar la ventana
        JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
        dispose();
    }
}
