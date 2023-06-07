package Datos;

import Entidades.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HabitacionNumDB {

    Connection cn;
    Conexion conect = new Conexion();

    public ArrayList<HabitacionEntity> GetCargarHabitacionesNum(String Sede, String Piso) {
        ArrayList<HabitacionEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "Select Se.CodSede as [CodSede],NumHabitacion as [NumHa]  from Habitacion \n"
                    + "inner join Sede as Se\n"
                    + "on Se.CodSede= Se.CodSede where CodSede ='" + Sede 
                    + "' and CodNivel ='" + Piso + "'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            HabitacionEntity Item = new HabitacionEntity();
            while (rs.next()) {
                Item = null;
                Item = new HabitacionEntity();
                Item.setSede(rs.getString("CodSede"));
                Item.setNumHa(rs.getString("NumHabitacion"));
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
