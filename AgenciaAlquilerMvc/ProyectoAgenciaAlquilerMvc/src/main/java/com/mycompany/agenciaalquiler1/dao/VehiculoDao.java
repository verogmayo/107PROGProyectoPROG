/*
 * Hola Mundo
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.agenciaalquiler1.dao;

import com.mycompany.agenciaalquiler1.modelo.Vehiculo;
import com.mycompany.agenciaalquiler1.modelo.Vehiculo;
import com.mycompany.agenciaalquiler1.modelo.Vehiculo;
import java.util.List;

/**
 *
 * @author veronique.gru
 */
public interface VehiculoDao {
     List<Vehiculo> listar() throws DaoException;
    int insertar(List<Vehiculo> vehiculos) throws DaoException;
}
