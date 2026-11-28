package dao;

import models.VentaModel;

import java.util.List;

public interface VentaDAO extends GenericDAO<VentaModel, Integer> {
    List<VentaModel> listarVentasPorIdEmpleado(String idEmpleado);
}
