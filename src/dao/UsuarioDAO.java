package dao;

import models.UsuarioModel;

public interface UsuarioDAO extends GenericDAO<UsuarioModel, Integer> {
    UsuarioModel autenticar(String usuario, String contrasena);
}
