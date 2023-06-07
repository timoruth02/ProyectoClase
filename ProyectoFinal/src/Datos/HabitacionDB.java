package Datos;

import Entidades.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HabitacionDB {

    Connection cn;
    Conexion conect = new Conexion();

    public ArrayList<HabitacionEntity> GetCargarHabitaciones() {
        ArrayList<HabitacionEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "select \n"
                    + "H.NumHabitacion as [NumHabitacion],\n"
                    + "H.Estado_Habitacion as [Estado_Habitacion],\n"
                    + "N.Nivel_Piso as [Piso],\n"
                    + "T_H.Descripcion_TipoHabitacion as [Tipo]\n"
                    + "from Habitacion as H\n"
                    + "inner join Nivel as N \n"
                    + "On N.CodNivel = H.CodNivel \n"
                    + "inner join Tipo_Habitacion as T_H\n"
                    + "on T_H.CodTipo_Habitacion = H.CodTipo_Habitacion";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            HabitacionEntity Item = new HabitacionEntity();
            while (rs.next()) {
                Item = null;
                Item = new HabitacionEntity();
                Item.setNumHabitacion(rs.getString("NumHabitacion"));
                Item.setEstado_Habitacion(rs.getString("Estado_Habitacion"));
                Item.setPiso(rs.getString("Piso"));
                Item.setTipo(rs.getString("Tipo"));
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
