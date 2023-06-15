package Datos;

import Entidades.UbigeoEntity;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UbigeoDB {
    Connection cn;
    Conexion conect = new Conexion();

    public ArrayList<UbigeoEntity> getBuscarUbigeo (String Cod_Ubigeo ) {
        ArrayList<UbigeoEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "Select Distrito,Provincia,Departamento from Ubigeo  where CodUbigeo = '"+ Cod_Ubigeo +"'";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            UbigeoEntity Item = new UbigeoEntity();
            while (rs.next()) {
                Item = null;
                Item = new UbigeoEntity();
                Item.setDistrito(rs.getString("Distrito"));
                Item.setProvincia(rs.getString("Provincia"));
                Item.setDepartamento(rs.getString("Departamento"));                
                Lista.add(Item);
            }
            cmd.close();
            cn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Lista;
    }
    
     public ArrayList<UbigeoEntity> getBuscarUbigeoItems ( ) {
        ArrayList<UbigeoEntity> Lista = new ArrayList<>();

        try {
            cn = conect.conectar();
            String sql = "Select Distrito,Provincia,Departamento from Ubigeo  ";
            CallableStatement cmd = cn.prepareCall(sql);
            ResultSet rs = cmd.executeQuery();
            UbigeoEntity Item = new UbigeoEntity();
            while (rs.next()) {
                Item = null;
                Item = new UbigeoEntity();
                Item.setDistrito(rs.getString("Distrito"));
                Item.setProvincia(rs.getString("Provincia"));
                Item.setDepartamento(rs.getString("Departamento"));                
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
