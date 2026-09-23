package com.appregistros.appregistro.controller;

import com.appregistros.appregistro.model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Detalleclientecontroller {

    @FXML private Label lblNombre;
    @FXML private Label lblApellido;
    @FXML private Label lblTipoCliente;
    @FXML private Label lblCiudad;
    @FXML private Label lblFechaNacimiento;
    @FXML private Label lblTipoSolicitud;
    @FXML private Label lblServicios;

    /** Recibe el Cliente seleccionado desde Consultaclientecontroller. */
    public void setCliente(Cliente cliente) {
        lblNombre.setText(cliente.getNombre());
        lblApellido.setText(cliente.getApellido());
        lblTipoCliente.setText(cliente.getTipoCliente());
        lblCiudad.setText(cliente.getCiudad());
        lblFechaNacimiento.setText(String.valueOf(cliente.getFechaNacimiento()));
        lblTipoSolicitud.setText(cliente.getTipoSolicitud());
        lblServicios.setText(cliente.getServicioInteres());
    }

    @FXML
    private void cerrar() {
        Stage stage = (Stage) lblNombre.getScene().getWindow();
        stage.close();
    }
}