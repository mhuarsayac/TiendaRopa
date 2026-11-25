package models;

public class UsuarioModel {
    private int idUsuario;
    private String usuario;
    private String contrasena;
    private String activo;

    public UsuarioModel(int idUsuario, String usuario, String contrasena, String activo) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.activo = activo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public void mostrarInformacion() {
        System.out.println("ID_USUARIO: " + idUsuario + " - USUARIO: " + usuario + " - CONTRASEÑA: " + contrasena +
                " - ACTIVO: " + activo);
    }
}
