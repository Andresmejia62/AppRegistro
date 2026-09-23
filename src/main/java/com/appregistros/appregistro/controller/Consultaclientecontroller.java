package com.appregistros.appregistro.controller;

import com.appregistros.appregistro.model.Cliente;
import com.appregistros.appregistro.util.DataStore;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Consultaclientecontroller {

    @FXML private TextField txtBuscar;
    @FXML private Button btnBuscar;
    @FXML private TableView<Cliente> tblClientes;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colApellido;
    @FXML private TableColumn<Cliente, String> colTipoCliente;
    @FXML private TableColumn<Cliente, String> colCiudad;
    @FXML private TableColumn<Cliente, String> colFechaNacimiento;
    @FXML private TableColumn<Cliente, String> colTipoSolicitud;
    @FXML private Button btnVolver;

    @FXML
    private void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colTipoCliente.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colTipoSolicitud.setCellValueFactory(new PropertyValueFactory<>("tipoSolicitud"));

        tblClientes.setItems(DataStore.getInstancia().getClientes());
    }

    @FXML
    private void buscarCliente() {
        String texto = txtBuscar.getText() == null ? "" : txtBuscar.getText().trim().toLowerCase();
        if (texto.isEmpty()) {
            tblClientes.setItems(DataStore.getInstancia().getClientes());
            return;
        }
        var filtrados = DataStore.getInstancia().getClientes().filtered(c ->
                c.getNombre().toLowerCase().contains(texto) ||
                        c.getApellido().toLowerCase().contains(texto));
        tblClientes.setItems(filtrados);
    }

    @FXML
    private void manejarDobleClic(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Cliente seleccionado = tblClientes.getSelectionModel().getSelectedItem();
            if (seleccionado != null) {
                abrirDetalle(seleccionado);
            }
        }
    }

    private void abrirDetalle(Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/appregistros/appregistro/DetalleCliente.fxml"));
            Parent raiz = loader.load();
            Detalleclientecontroller controlador = loader.getController();
            controlador.setCliente(cliente);

            Stage stage = new Stage();
            stage.setTitle("Detalle del cliente");
            stage.setScene(new Scene(raiz));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void volver() {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }
}