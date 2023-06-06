package Datos;

import Entidades.ClienteEntity;
import Entidades.DocIdentidadEntity;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDB {

    Connection cn;
    Conexion conect = new Conexion();

    public ArrayList<ClienteEntity> getBuscarCliente(String Documento ) {
        ArrayList<ClienteEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "Select NumDoc_Identidad,Apellidos,Nombres,Direccion,CodNac from Cliente  where NumDoc_Identidad = '"+ Documento +"'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            ClienteEntity Item = new ClienteEntity();
            while (rs.next()) {
                Item = null;
                Item = new ClienteEntity();
                Item.setNumDoc_Identidad(rs.getString("NumDoc_Identidad"));
                Item.setApellidos(rs.getString("Apellidos"));
                Item.setNombres(rs.getString("Nombres"));
                Item.setDireccion(rs.getString("Direccion"));
                Item.setCodNac(rs.getString("CodNac"));

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
