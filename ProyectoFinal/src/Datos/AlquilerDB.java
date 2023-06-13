/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Entidades.AlquilerEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;

/**
 *
 *
 */
public class AlquilerDB {

    Connection cn;
    Conexion conect = new Conexion();

    public boolean Save(AlquilerEntity Item) {

        try {
            cn = conect.conectar();
            PreparedStatement st = cn.prepareStatement("INSERT INTO Alquiler(NumAlquiler,CodSede,CodCliente,CodEmpleado,NumHabitacion,Fec_Alquiler,Fec_TerminoAlquiler,Importe)\n"
                    + "VALUES (?,?,?,?,?,?,?,?)");
            st.setString(1, Item.getNumAlquiler());
            st.setString(2, Item.getCodSede());
            st.setString(3, Item.getCodCliente());
            st.setString(4, Item.getCodEmpleado());
            st.setString(5, Item.getNumHabitacion());
            st.setDate(6,  new java.sql.Date(Item.getFec_Alquiler().getDate()));
            st.setDate(7,  new java.sql.Date(Item.getFec_TerminoAlquiler().getDate()));
            st.setDouble(8, Item.getImporte());
            // execute the preparedstatement insert
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return true;
    }
}
