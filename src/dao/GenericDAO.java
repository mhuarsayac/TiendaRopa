package dao;

import java.util.List;

public interface GenericDAO<T,K> {
    boolean insertar(T producto);
    List<T> listarTodos();
    T buscarPorCodigo(K codigo);
    boolean actualizar(T producto);
    boolean eliminar(K codigo);
}
