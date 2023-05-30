package Datos;

import Entidades.DocIdentidadEntity;
import Entidades.SedeEntity;
import Entidades.Tipo_HabitacionEntity;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Tipo_HabitacionDB {

Connection cn;
    Conexion conect = new Conexion();

    public ArrayList<Tipo_HabitacionEntity> getCodTipo_HabitacionItems() {
        ArrayList<Tipo_HabitacionEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "select CodTipo_Habitacion, Descripcion_TipoHabitacion from Tipo_Habitacion";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            Tipo_HabitacionEntity Item = new Tipo_HabitacionEntity();
            while (rs.next()) {
                Item = null;
                Item = new Tipo_HabitacionEntity();
                Item.setCodTipo_Habitacion(rs.getString("CodTipo_Habitacion"));
                Item.setDescripcion_TipoHabitacion(rs.getString("Descripcion_TipoHabitacion"));
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
