package controllers;

import dao.UsuarioDAO;
import dao.impl.UsuarioDAOImpl;
import models.UsuarioModel;

public class UsuarioController {
    private final UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

    public void registrar(int idUsuario, String usuario, String contrasena, String activo) {
        UsuarioModel p = new UsuarioModel(idUsuario, usuario, contrasena, activo);
        if (usuarioDAO.insertar(p)) {
            System.out.println("Usuario registrado.");
        } else {
            System.out.println("No se pudo registrar el usuario.");
        }
    }

    public void mostrarTodos() {
        for (UsuarioModel c : usuarioDAO.listarTodos()) {
            c.mostrarInformacion();
        }
    }

    public void mostrarUsuarioPorCodigo(int idUsuario) {
        UsuarioModel usuarioBuscado = usuarioDAO.buscarPorCodigo(idUsuario);
        if (usuarioBuscado != null) {
            usuarioBuscado.mostrarInformacion();
        } else {
            System.out.println("No se pudo encontrar el usuario");
        }
    }

    public void actualizarUsuario(int idUsuario, String usuario, String contrasena, String activo) {
        UsuarioModel usuarioActualizable = usuarioDAO.buscarPorCodigo(idUsuario);
        if (usuarioActualizable != null) {
            System.out.println("El usuario fue encontrado");
            usuarioActualizable.setUsuario(usuario);
            usuarioActualizable.setContrasena(contrasena);
            usuarioActualizable.setActivo(activo);
            if (usuarioDAO.actualizar(usuarioActualizable)) {
                System.out.println("El usuario fue actualizado");
            } else {
                System.out.println("El usuario no fue actualizado");
            }
        } else {
            System.out.println("No se pudo encontrar el usuario");
        }
    }

    public void eliminarUsuario(int idUsuario) {
        if (usuarioDAO.eliminar(idUsuario)) {
            System.out.println("Se elimino el usuario");
        } else {
            System.out.println("No se elimino el usuario");
        }
    }
}
