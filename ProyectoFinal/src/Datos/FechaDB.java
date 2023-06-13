package Datos;

import Entidades.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;


public class FechaDB {
       Connection cn;
    Conexion conect = new Conexion();

    public ArrayList<FechaEntity> getFecha() {
        ArrayList<FechaEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "Select GETDATE() as Fecha";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            FechaEntity Item = new FechaEntity();
            while (rs.next()) {
                Item = null;
                Item = new FechaEntity();
                Item.setFecha(rs.getDate("Fecha"));

                Lista.add(Item);
            }
            cmd.close();
            cn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Lista;
    }
    
     public ArrayList<FechaEntity> getFechaDia(String Fecha) {
        ArrayList<FechaEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "SELECT DATEADD(DAY, 1, GETDATE()) AS "+Fecha+"'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            FechaEntity Item = new FechaEntity();
            while (rs.next()) {
                Item = null;
                Item = new FechaEntity();
                Item.setFecha(rs.getDate("Fecha"));

                Lista.add(Item);
            }
            cmd.close();
            cn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Lista;
    }
    
    public ArrayList<FechaEntity> getFechaHora(String Fecha) {
        ArrayList<FechaEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "SELECT DATEADD(HOUR, 7, GETDATE()) AS "+Fecha+"'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            FechaEntity Item = new FechaEntity();
            while (rs.next()) {
                Item = null;
                Item = new FechaEntity();
                Item.setFecha(rs.getDate("Fecha"));

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
