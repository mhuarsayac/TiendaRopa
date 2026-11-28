package dao;

import models.EmpleadoModel;

public interface EmpleadoDAO extends GenericDAO<EmpleadoModel, String>{
    EmpleadoModel buscarPorIdUsuario(int idUsuario);
}