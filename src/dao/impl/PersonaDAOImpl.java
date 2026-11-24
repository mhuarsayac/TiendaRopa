package dao.impl;

import dao.PersonaDAO;
import models.PersonaModel;
import services.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonaDAOImpl implements PersonaDAO {

    @Override
    public boolean insertar(PersonaModel personaModel) {
        String sql = "INSERT INTO persona (id_persona, dni, primer_nombre, segundo_nombre, "
                + "apellido_paterno, apellido_materno, direccion, distrito, "
                + "codigo_postal, telefono, correo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection cn = DatabaseConnector.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, personaModel.getIdPersona());
            ps.setString(2, personaModel.getDni());
            ps.setString(3, personaModel.getPrimerNombre());
            ps.setString(4, personaModel.getSegundoNombre());
            ps.setString(5, personaModel.getApellidoPaterno());
            ps.setString(6, personaModel.getApellidoMaterno());
            ps.setString(7, personaModel.getDireccion());
            ps.setString(8, personaModel.getDistrito());
            ps.setString(9, personaModel.getCodigoPostal()); // recuerda corregir 'coidgoPostal' en PersonaModel
            ps.setString(10, personaModel.getTelefono());
            ps.setString(11, personaModel.getCorreo());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar persona: " + e.getMessage());
            return false;
        }
    }
}