package org.example.modelo;

public abstract class Persona implements IIniciarSesion,IMonitoreo{
    private String nombres;
    private String apellidos;
    private Integer documento;
    private Integer celular;
    private String usuario;
    private String contrasena;
    private  String email;

    public Persona(){}

    public Persona(String nombres, String apellidos, Integer documento, Integer celular, String usuario, String contrasena, String email) {

        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        this.celular = celular;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.email = email;
    }

    public Persona(String nombres,Integer documento) {
        this.nombres = nombres;
        this.documento = documento;
    }

    public  Persona(String nombres, String apellido){
        this.nombres = nombres;
        this.apellidos = apellido;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public Integer getCelular() {
        return celular;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    }

