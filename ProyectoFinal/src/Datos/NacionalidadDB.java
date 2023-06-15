package Datos;

import Entidades.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author PRV
 */
public class NacionalidadDB {

    Connection cn;
    Conexion conect = new Conexion();

    public ArrayList<NacionalidadEntity> GetBuscarNacionalidad(String CodNac) {
        ArrayList<NacionalidadEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "select Gentilicio_Nac from Nacionalidad where  CodNac='" + CodNac + "'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            NacionalidadEntity Item = new NacionalidadEntity();
            while (rs.next()) {
                Item = null;
                Item = new NacionalidadEntity();
                Item.setGentilicio_Nac(rs.getString("Gentilicio_Nac"));
                Lista.add(Item);
            }
            cmd.close();
            cn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Lista;
    }
     public ArrayList<NacionalidadEntity> GetBuscarNacionalidadItems() {
        ArrayList<NacionalidadEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "select CodNac, Gentilicio_Nac from Nacionalidad ";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            NacionalidadEntity Item = new NacionalidadEntity();
            while (rs.next()) {
                Item = null;
                Item = new NacionalidadEntity();
                Item.setCodNac(rs.getString("CodNac"));
                Item.setGentilicio_Nac(rs.getString("Gentilicio_Nac"));
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
