package Datos;

import Entidades.Reserva_AlquilerEntity;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;


public class Reserva_AlquilerDB {
    Connection cn;
    Conexion conect = new Conexion();
    
    public ArrayList<Reserva_AlquilerEntity> getCodDoc_IdentidadItems() {
        ArrayList<Reserva_AlquilerEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "select NumReserva_Habitacion,NumAlquiler from Reserva_Alquiler";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            Reserva_AlquilerEntity Item = new Reserva_AlquilerEntity();
            while (rs.next()) {
                Item = null;
                Item = new Reserva_AlquilerEntity();
                Item.setNumReserva_Habitacion(rs.getString("NumReserva_Habitacion"));
                Item.setNumAlquiler(rs.getString("NumAlquiler"));
                Lista.add(Item);
            }
            cmd.close();
            cn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Lista;
    }
}
