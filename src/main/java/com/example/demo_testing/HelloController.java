// LoginController.java
package com.example.demo_testing;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;

import java.io.IOException;

public class HelloController extends HelloApplication {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private Stage stage;
    private Scene scene;
//    private Parent root;

    // Method to handle login button click
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Process the username and password here (e.g., authenticate)
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        // Add your login logic here (e.g., checking credentials)
        if (authenticate(username, password)) {
            System.out.println("Login successful");
        } else {
            System.out.println("Login failed");
        }
    }

    @FXML
    private void openRegisterWindow(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Dummy authentication method
    private boolean authenticate(String username, String password) {
        // Replace this with your actual authentication logic
        return "kodomotachi".equals(username) && "kodomotachi".equals(password);
    }
}
