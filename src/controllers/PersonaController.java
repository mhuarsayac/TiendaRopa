package controllers;

import dao.PersonaDAO;
import dao.impl.PersonaDAOImpl;
import models.PersonaModel;

public class PersonaController {

    private final PersonaDAO personaDAO = new PersonaDAOImpl();

    public void registrar(String idPersona, String dni, String primerNombre,
                          String segundoNombre, String apellidoPaterno, String apellidoMaterno,
                          String direccion, String distrito, String codigoPostal,
                          String telefono, String correo) {
        PersonaModel persona = new PersonaModel(
                idPersona, dni, primerNombre, segundoNombre,
                apellidoPaterno, apellidoMaterno, direccion, distrito,
                codigoPostal, telefono, correo
        );

        if (personaDAO.insertar(persona) == true) {
            System.out.println("Persona registrada.");
        } else {
            System.out.println("No se pudo registrar la persona.");
        }
    }



}