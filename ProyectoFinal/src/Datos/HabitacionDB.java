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

    public ArrayList<HabitacionEntity> GetCargarHabitacionesParametro(String Sede, String Piso) {
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

    public ArrayList<HabitacionEntity> GetCargarHabitacionesNum(String Sede, String Piso, String Tipo_Habitacion) {
        ArrayList<HabitacionEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "Select NumHabitacion from Habitacion\n"
                    + "where CodSede ='" + Sede + "' AND CodNivel ='"
                    + Piso + "' and CodTipo_Habitacion = '" + Tipo_Habitacion + "'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            HabitacionEntity Item = new HabitacionEntity();
            while (rs.next()) {
                Item = null;
                Item = new HabitacionEntity();
                Item.setNumHabitacion(rs.getString("NumHabitacion"));
                Lista.add(Item);
            }
            cmd.close();
            cn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Lista;
    }

    public ArrayList<HabitacionEntity> GetCargarHabitacionesTarifa(String NumHabitacion) {
        ArrayList<HabitacionEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "select NumHabitacion,Precio_Hora,Precio_Noche from Habitacion\n"
                    + "where NumHabitacion= '" + NumHabitacion + "'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            HabitacionEntity Item = new HabitacionEntity();
            while (rs.next()) {
                Item = null;
                Item = new HabitacionEntity();
                Item.setNumHabitacion(rs.getString("NumHabitacion"));
                Item.setPrecio_Hora(rs.getDouble("Precio_Hora"));
                Item.setPrecio_Noche(rs.getDouble("Precio_Noche"));
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
