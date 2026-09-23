package com.appregistros.appregistro.model;

import java.time.LocalDate;

public class Cliente {
    private String Nombre;
    private String Apellido;
    private String TipoCliente;
    private String Ciudad;
    private LocalDate FechaNacimiento;
    private String TipoSolicitud;
    private String ServicioInteres;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String tipoCliente, String ciudad, LocalDate fechaNacimiento, String tipoSolicitud, String servicioInteres) {
        Nombre = nombre;
        Apellido = apellido;
        TipoCliente = tipoCliente;
        Ciudad = ciudad;
        FechaNacimiento = fechaNacimiento;
        TipoSolicitud = tipoSolicitud;
        ServicioInteres = servicioInteres;
    }

    public String getNombre() { return Nombre; }
    public void setNombre(String nombre) { Nombre = nombre; }

    public String getApellido() { return Apellido; }
    public void setApellido(String apellido) { Apellido = apellido; }

    public String getTipoCliente() { return TipoCliente; }
    public void setTipoCliente(String tipoCliente) { TipoCliente = tipoCliente; }

    public String getCiudad() { return Ciudad; }
    public void setCiudad(String ciudad) { Ciudad = ciudad; }

    public LocalDate getFechaNacimiento() { return FechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { FechaNacimiento = fechaNacimiento; }

    public String getTipoSolicitud() { return TipoSolicitud; }
    public void setTipoSolicitud(String tipoSolicitud) { TipoSolicitud = tipoSolicitud; }

    public String getServicioInteres() { return ServicioInteres; }
    public void setServicioInteres(String servicioInteres) { ServicioInteres = servicioInteres; }
}