package controllers;

import dao.EmpleadoDAO;
import dao.UsuarioDAO;
import dao.impl.EmpleadoDAOImpl;
import dao.impl.UsuarioDAOImpl;
import models.EmpleadoModel;
import models.UsuarioModel;
import utils.Validador;

public class LoginController {
    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
    private final EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl();

    public EmpleadoModel iniciarSesion(String usuario, String contrasena) {
        UsuarioModel cuenta = usuarioDAO.autenticar(usuario, contrasena);

        if (cuenta == null) {
            System.out.println("Usuario o contraseña incorrectos");
            return null;
        }

        if (!Validador.esActivo(cuenta.getActivo())) {
            System.out.print("Esta cuenta esta inactiva. Contacta con el administrador");
            return null;
        }

        EmpleadoModel empleado = empleadoDAO.buscarPorIdUsuario(cuenta.getIdUsuario());
        if (empleado == null) {
            System.out.println("Esta cuenta no esta asociada a ningun empleado");
            return null;
        }

        if (!Validador.estaEn(empleado.getEstado(), "ACTIVO")) {
            System.out.println("El empleado esta " + empleado.getEstado() + ". No puede ingresar");
            return null;
        }
        return empleado;
    }
}
