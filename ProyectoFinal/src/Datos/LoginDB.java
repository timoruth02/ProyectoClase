/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.3tteterterterDAVIDsxfsgfsgfsf
 */
package Datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author Ruth Sayuri
 */
public class LoginDB {

    Connection cn;

    public LoginDB() {
        Conexion con = new Conexion();
        cn = con.conectar();
    }

    public void LLenarDatos() {
        try {
            String sql = "select * from Productos";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {
                Object[] datos = new Object[4];

                for (int i = 0; i < 4; i++) {
                    datos[i] = rs.getString(i + 1);
                    System.out.println(rs.getString(i + 1));
                }
//                modelo.addRow(datos);datos
            }
            cmd.close();
            cn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
