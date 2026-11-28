package controllers;

import dao.UsuarioDAO;
import dao.impl.UsuarioDAOImpl;
import models.EmpleadoModel;
import models.UsuarioModel;

public class LoginController {
    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    public EmpleadoModel iniciarSesion(String usuario, String contrasena) {
        UsuarioModel cuenta = usuarioDAO.autenticar(usuario, contrasena);

        if (cuenta == null){
            System.out.println("Usuario o contraseña incorrectos");
            return null;
        }

        return null;
    }
}
