/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package com.mycompany.agenciaalquiler1;

import com.mycompany.agenciaalquiler1.controlador.Controlador;
import com.mycompany.agenciaalquiler1.modelo.AgenciaAlquiler;
import com.mycompany.agenciaalquiler1.vista.Ventana;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author Véro
 * @version 1.0
 * @since 11 may 2025
 */
public class AppAgenciaAlquierMvc {
 private static final Logger LOG = Logger.getLogger("com.mycompany.agenciaalquiler1");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
         LogManager.getLogManager().readConfiguration(ClassLoader.getSystemClassLoader().getResourceAsStream("mylogging.properties"));
         Ventana vista=new Ventana();
         AgenciaAlquiler modelo=new AgenciaAlquiler();
         Controlador controlador=new Controlador(vista,modelo);
         vista.setControlador(controlador);
         controlador.iniciar();
    
    }

}
