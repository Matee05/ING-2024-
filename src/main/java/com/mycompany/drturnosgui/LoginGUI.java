package com.mycompany.drturnosgui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class LoginGUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private JButton createUserButton;
    private JTextArea messageArea; // Área para mostrar mensajes
    private ActionListener loginListener;

    // Conjunto de usuarios
    private Set<User> users = new HashSet<>();

    public LoginGUI() {
        setTitle("Login");
        setSize(300, 200); // Aumentar el tamaño para incluir el área de mensajes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        loadUsers(); // Cargar usuarios al iniciar
        createUI();
    }

    private void createUI() {
        JPanel panel = new JPanel(new GridLayout(6, 2)); // Aumentar a 6 filas para más campos
        panel.add(new JLabel("Usuario:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(e -> handleLogin());
        panel.add(loginButton);

        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> System.exit(0));
        panel.add(cancelButton);

        createUserButton = new JButton("Crear Usuario");
        createUserButton.addActionListener(e -> openCreateUserDialog());
        panel.add(createUserButton);

        messageArea = new JTextArea(3, 20); // Aumentar el área de texto
        messageArea.setEditable(false);
        panel.add(messageArea);

        add(panel);
    }

    // Método para manejar el inicio de sesión
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        // Verificar las credenciales
        if (authenticate(username, password)) {
            // Notificar al oyente que el inicio de sesión fue exitoso
            if (loginListener != null) {
                loginListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "loginSuccess"));
            }
            dispose(); // Cerrar el diálogo de inicio de sesión
        } else {
            messageArea.setText("Credenciales inválidas.");
        }
    }

    // Autenticación de usuario
    private boolean authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Crear nuevo usuario
    private void openCreateUserDialog() {
        String username = JOptionPane.showInputDialog(this, "Ingrese un nuevo nombre de usuario:");
        if (username != null && !username.trim().isEmpty()) {
            String password = JOptionPane.showInputDialog(this, "Ingrese la contraseña:");
            if (password != null) {
                createUser(username, password);
                saveUsers(); // Guardar usuarios después de la creación
                messageArea.setText("Usuario creado con éxito.");
            }
        }
    }

    // Crear un nuevo usuario
    private void createUser(String username, String password) {
        users.add(new User(username, password));
    }

    // Guardar usuarios en un archivo
    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.ser"))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Cargar usuarios desde un archivo
    private void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.ser"))) {
            users = (Set<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Manejar el caso donde no hay usuarios existentes
            users = new HashSet<>();
        }
    }

    // Método para agregar un oyente de inicio de sesión
    public void addLoginListener(ActionListener listener) {
        this.loginListener = listener;
    }
}
