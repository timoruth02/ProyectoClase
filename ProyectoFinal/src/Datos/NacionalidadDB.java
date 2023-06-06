/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
}
