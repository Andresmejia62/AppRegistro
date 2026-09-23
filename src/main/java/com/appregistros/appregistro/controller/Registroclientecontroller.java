package com.appregistros.appregistro.controller;

import java.io.File;
import java.time.LocalDate;

import com.appregistros.appregistro.model.Cliente;
import com.appregistros.appregistro.util.DataStore;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Registroclientecontroller {

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private ComboBox<String> cmbTipoCliente;
    @FXML private ComboBox<String> cmbCiudad;
    @FXML private DatePicker dpFechaNacimiento;
    @FXML private RadioButton rbNuevaSolicitud;
    @FXML private RadioButton rbActualizacion;
    @FXML private RadioButton rbReclamo;
    @FXML private CheckBox chkInternet;
    @FXML private CheckBox chkTelefonia;
    @FXML private CheckBox chkCable;
    @FXML private CheckBox chkStreaming;
    @FXML private ImageView imgFoto;
    @FXML private Button btnSeleccionarFoto;
    @FXML private Button btnGuardar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnCancelar;

    private File fotoSeleccionada;

    @FXML
    private void initialize() {
        cmbTipoCliente.getItems().addAll("Civil", "Empresa", "Estudiante", "Docente");
        cmbCiudad.getItems().addAll("Managua", "Esteli", "Rivas", "Boaco", "Granada", "Otra");

        ToggleGroup grupoTipoSolicitud = new ToggleGroup();
        rbNuevaSolicitud.setToggleGroup(grupoTipoSolicitud);
        rbActualizacion.setToggleGroup(grupoTipoSolicitud);
        rbReclamo.setToggleGroup(grupoTipoSolicitud);

        imgFoto.setFitWidth(180);
        imgFoto.setFitHeight(180);
        imgFoto.setPreserveRatio(true);
    }

    @FXML
    private void seleccionarFoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar fotografía");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        File archivo = fileChooser.showOpenDialog(btnSeleccionarFoto.getScene().getWindow());
        if (archivo == null) return;

        fotoSeleccionada = archivo;
        imgFoto.setImage(new Image(archivo.toURI().toString()));
    }

    @FXML
    private void guardarCliente() {
        String nombre = txtNombre.getText() == null ? "" : txtNombre.getText().trim();
        String apellido = txtApellido.getText() == null ? "" : txtApellido.getText().trim();
        String tipoCliente = cmbTipoCliente.getValue();
        String ciudad = cmbCiudad.getValue();
        LocalDate fechaNacimiento = dpFechaNacimiento.getValue();
        String tipoSolicitud = obtenerTipoSolicitudSeleccionado();
        String servicios = obtenerServiciosSeleccionados();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            mostrarAlerta("Validación", "Los nombres y apellidos son obligatorios.");
            return;
        }
        if (tipoCliente == null) {
            mostrarAlerta("Validación", "Debe seleccionar el tipo de cliente.");
            return;
        }
        if (ciudad == null) {
            mostrarAlerta("Validación", "Debe seleccionar una ciudad.");
            return;
        }
        if (fechaNacimiento == null) {
            mostrarAlerta("Validación", "Debe ingresar la fecha de nacimiento.");
            return;
        }
        if (fechaNacimiento.isAfter(LocalDate.now())) {
            mostrarAlerta("Validación", "La fecha de nacimiento no puede ser futura.");
            return;
        }
        if (tipoSolicitud == null) {
            mostrarAlerta("Validación", "Debe seleccionar un tipo de solicitud.");
            return;
        }
        if (servicios.isEmpty()) {
            mostrarAlerta("Validación", "Debe seleccionar al menos un servicio de interés.");
            return;
        }
        if (fotoSeleccionada == null) {
            mostrarAlerta("Validación", "Debe seleccionar una fotografía antes de guardar.");
            return;
        }

        Cliente cliente = new Cliente(nombre, apellido, tipoCliente, ciudad,
                fechaNacimiento, tipoSolicitud, servicios);
        DataStore.getInstancia().agregarCliente(cliente);

        mostrarAlerta("Éxito", "Cliente registrado correctamente:\n" + nombre + " " + apellido
                + "\nTipo: " + tipoCliente + "\nCiudad: " + ciudad + "\nSolicitud: " + tipoSolicitud);
        limpiarFormulario();
    }

    private String obtenerServiciosSeleccionados() {
        StringBuilder sb = new StringBuilder();
        if (chkInternet.isSelected()) sb.append("Internet, ");
        if (chkTelefonia.isSelected()) sb.append("Telefonía, ");
        if (chkCable.isSelected()) sb.append("Cable, ");
        if (chkStreaming.isSelected()) sb.append("Streaming, ");
        if (sb.length() > 0) sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    @FXML
    private void limpiarFormulario() {
        txtNombre.clear();
        txtApellido.clear();
        cmbTipoCliente.setValue(null);
        cmbCiudad.setValue(null);
        dpFechaNacimiento.setValue(null);
        rbNuevaSolicitud.setSelected(false);
        rbActualizacion.setSelected(false);
        rbReclamo.setSelected(false);
        chkInternet.setSelected(false);
        chkTelefonia.setSelected(false);
        chkCable.setSelected(false);
        chkStreaming.setSelected(false);
        imgFoto.setImage(null);
        fotoSeleccionada = null;
        txtNombre.requestFocus();
    }

    @FXML
    private void cancelar() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    private String obtenerTipoSolicitudSeleccionado() {
        if (rbNuevaSolicitud.isSelected()) return rbNuevaSolicitud.getText();
        if (rbActualizacion.isSelected()) return rbActualizacion.getText();
        if (rbReclamo.isSelected()) return rbReclamo.getText();
        return null;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}