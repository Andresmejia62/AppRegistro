package com.appregistros.appregistro.util;

import com.appregistros.appregistro.model.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataStore {

    private static DataStore instancia;
    private final ObservableList<Cliente> clientes = FXCollections.observableArrayList();

    private DataStore() { }

    public static DataStore getInstancia() {
        if (instancia == null) {
            instancia = new DataStore();
        }
        return instancia;
    }

    public ObservableList<Cliente> getClientes() {
        return clientes;
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }
}