package com.appregistros.appregistro.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class Ventanaprincipalcontroller {

    @FXML private Label lblBienvenida;
    @FXML private Button btnRegistrarCliente;
    @FXML private Button btnConsultarClientes;
    @FXML private Button btnSalir;

    @FXML
    private void abrirRegistro(ActionEvent event) {
        try {
            Parent raiz = FXMLLoader.load(
                    getClass().getResource("/com/appregistros/appregistro/RegistroCliente.fxml"));
            Stage nuevaVentana = new Stage();
            nuevaVentana.setTitle("Registro de cliente");
            nuevaVentana.setScene(new Scene(raiz));
            nuevaVentana.show();
        } catch (IOException e) {
            mostrarError("No se pudo abrir Registro de cliente: " + e.getMessage());
        }
    }

    @FXML
    private void abrirConsulta(ActionEvent event) {
        try {
            Parent raiz = FXMLLoader.load(
                    getClass().getResource("/com/appregistros/appregistro/ConsultaCliente.fxml"));
            Stage nuevaVentana = new Stage();
            nuevaVentana.setTitle("Consulta de clientes");
            nuevaVentana.setScene(new Scene(raiz));
            nuevaVentana.show();
        } catch (IOException e) {
            mostrarError("No se pudo abrir Consulta de clientes: " + e.getMessage());
        }
    }

    @FXML
    private void mostrarInfo(ActionEvent event) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Información");
        info.setHeaderText(null);
        info.setContentText("Sistema de Registro y Consulta de Solicitudes.");
        info.showAndWait();
    }

    @FXML
    private void salir(ActionEvent event) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar salida");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Desea salir de la aplicación?");

        Optional<ButtonType> resultado = confirmacion.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            Stage stage = (Stage) lblBienvenida.getScene().getWindow();
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