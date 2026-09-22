package com.appregistros.appregistro.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Logincontroller {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;

    @FXML
    private void iniciarSesion(ActionEvent event) {
        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();

        if (usuario == null || usuario.isBlank() || password == null || password.isBlank()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Datos incompletos");
            alerta.setHeaderText(null);
            alerta.setContentText("Debe ingresar usuario y contraseña.");
            alerta.showAndWait();
            return;
        }
        if (!usuario.equals("admin") || !password.equals("1234")) {
            Alert alertaError = new Alert(Alert.AlertType.ERROR);
            alertaError.setTitle("Negativo Chele");
            alertaError.setHeaderText(null);
            alertaError.setContentText("Usuario o contraseña incorrectos ladron.");
            alertaError.showAndWait();
            return;
        }
        try {
            Parent raiz = FXMLLoader.load(
                    getClass().getResource("/com/appregistros/appregistro/VentanaPrincipal.fxml"));
            Stage stageActual = (Stage) txtUsuario.getScene().getWindow();
            stageActual.setScene(new Scene(raiz));
            stageActual.setTitle("Sistema de Registro - Menú principal");
        } catch (IOException e) {
            mostrarError("No se pudo abrir la ventana principal: " + e.getMessage());
        }
    }

    @FXML
    private void manejarTeclaEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            iniciarSesion(new ActionEvent());
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar salida");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Desea salir de la aplicación?");

        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            Stage stage = (Stage) txtUsuario.getScene().getWindow();
            stage.close();
        }
    }

    private void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}